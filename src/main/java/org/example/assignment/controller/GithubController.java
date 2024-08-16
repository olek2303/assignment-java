package org.example.assignment.controller;

import org.example.assignment.config.UserNotFoundException;
import org.example.assignment.service.GithubService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.example.assignment.dto.Repository;

import java.util.List;
import java.util.Map;


@Controller
@RequestMapping("/api/v1/github/")
public class GithubController {

    private final GithubService githubService;

    public GithubController(GithubService githubService) {
        this.githubService = githubService;
    }

    @GetMapping("/{username}")
    public ResponseEntity<List<Repository>> getRepositories(@PathVariable String username) {
        List<Repository> repositories = githubService.getRepositories(username);
        return ResponseEntity.ok(repositories);
    }

    @ExceptionHandler(UserNotFoundException.class)
    public ResponseEntity<Map<String, Object>> handleUserNotFound(UserNotFoundException ex) {
        return new ResponseEntity<>(
                Map.of(
                        "status", HttpStatus.NOT_FOUND.value(),
                        "message", ex.getMessage()
                ),
                HttpStatus.NOT_FOUND
        );
    }

}
