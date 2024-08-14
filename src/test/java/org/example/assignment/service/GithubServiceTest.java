package org.example.assignment.service;

import org.example.assignment.dto.Branch;
import org.example.assignment.dto.Repository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.web.client.RestTemplate;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class GithubServiceTest {

    @Autowired
    private GithubService githubService;

    @Test
    void getRepositories() {

        List<Repository> repositories = githubService.getRepositories("olek2303");
        assertNotNull(repositories);

        for (Repository repo : repositories) {
            System.out.println();
            System.out.println(repo.getRepositoryName());
            System.out.println(repo.getOwner().getLogin());
            for(Branch b : repo.getBranches()) {
                System.out.println("\t" + b.getName());
                System.out.println("\t\t" + b.getCommit().getSha());
            }
        }



    }
}