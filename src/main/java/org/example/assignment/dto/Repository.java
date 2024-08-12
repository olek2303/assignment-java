package org.example.assignment.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Repository {

    @JsonProperty("name")
    private String repositoryName;
    private Owner owner;
    private String lastCommitSHA;
    private String htmlUrl;
    private String description;

}
