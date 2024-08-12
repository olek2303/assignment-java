package org.example.assignment.service;

import org.example.assignment.dto.Repository;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.Map;

@Service
public class GithubService {

    private final RestTemplate restTemplate;

    public GithubService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public Repository[] getRepositories(String username) {
        String url = UriComponentsBuilder.fromHttpUrl("https://api.github.com/users/{username}/repos")
                .buildAndExpand(username)
                .toUriString();

        return restTemplate.getForObject(url, Repository[].class);
    }

    public String getCommitSHA(String username, String repoName) {
        String url = UriComponentsBuilder.fromHttpUrl("https://api.github.com/repos/{username}/{repoName}/commits")
                .buildAndExpand(username, repoName)
                .toUriString();

        Map[] commits = restTemplate.getForObject(url, Map[].class);

        if (commits != null && commits.length > 0) {
            return (String) commits[0].get("sha");
        }

        return null;
    }
}
