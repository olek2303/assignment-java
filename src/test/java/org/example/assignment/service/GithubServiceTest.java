package org.example.assignment.service;

import org.example.assignment.dto.Repository;
import org.junit.jupiter.api.Test;
import org.springframework.web.client.RestTemplate;

import static org.junit.jupiter.api.Assertions.*;

class GithubServiceTest {

    private final RestTemplate restTemplate = new RestTemplate();

    @Test
    void getRepositories() {

        GithubService gitHubService = new GithubService(restTemplate);
        Repository[] repositories = gitHubService.getRepositories("olek2303");

        for (Repository repo : repositories) {
            String name = repo.getRepositoryName();
            String login = repo.getOwner().getLogin();

            String commitSHA = gitHubService.getCommitSHA("olek2303", name);

            System.out.println("OwnerLogin: " + login +
                    "\nName: " + name +
                    "\nSHA: " + commitSHA);
        }

        assertNotNull(repositories);

    }
}