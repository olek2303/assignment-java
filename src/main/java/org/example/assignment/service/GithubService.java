package org.example.assignment.service;

import jakarta.annotation.PostConstruct;
import org.example.assignment.config.UserNotFoundException;
import org.example.assignment.dto.Branch;
import org.example.assignment.dto.Repository;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Service
public class GithubService {

    private final RestTemplate restTemplate;
    @Value("${github.token}")
    private String githubToken;

    public GithubService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    private HttpHeaders createHeaders() {
        HttpHeaders headers = new HttpHeaders();
        headers.set("Accept", "application/json");
        headers.set("Authorization", "Bearer " + githubToken);

        return headers;
    }

    public List<Repository> getRepositories(String username) {
        String url = UriComponentsBuilder.fromHttpUrl("https://api.github.com/users/{username}/repos")
                .buildAndExpand(username)
                .toUriString();

        try {
            HttpEntity<?> entity = new HttpEntity<>(createHeaders());
            ResponseEntity<Repository[]> response = restTemplate.exchange(url, HttpMethod.GET, entity, Repository[].class);

            if (response.getStatusCode() == HttpStatus.OK) {
                Repository[] repositories = response.getBody();
                List<Repository> nonForkedRepos = new ArrayList<>();

                for (Repository repo : repositories) {
                    if (!repo.isFork()) {
                        List<Branch> branches = getCommitShaForBranch(username, repo.getRepositoryName());
                        repo.setBranches(branches);
                        nonForkedRepos.add(repo);
                    }
                }

                return nonForkedRepos;
            } else {
                throw new RuntimeException("Failed to retrieve repositories");
            }
        } catch (HttpClientErrorException.NotFound ex) {
            throw new UserNotFoundException("User not found: " + username, ex);
        }
    }

    public List<Branch> getCommitShaForBranch(String username, String repoName) {
        String url = UriComponentsBuilder.fromHttpUrl("https://api.github.com/repos/{username}/{repoName}/branches")
                .buildAndExpand(username, repoName)
                .toUriString();

        try {
            HttpEntity<?> entity = new HttpEntity<>(createHeaders());
            ResponseEntity<Branch[]> response = restTemplate.exchange(url, HttpMethod.GET, entity, Branch[].class);

            if (response.getStatusCode() == HttpStatus.OK) {
                return List.of(Objects.requireNonNull(response.getBody()));
            } else {
                throw new RuntimeException("Failed to retrieve branches");
            }
        } catch (HttpClientErrorException e) {
            throw new RuntimeException("An error occurred: " + e.getMessage());
        }
    }
}
