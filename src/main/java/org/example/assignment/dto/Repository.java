package org.example.assignment.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@JsonIgnoreProperties(ignoreUnknown = true)
public class Repository {

    @JsonProperty("name")
    private String repositoryName;
    private Owner owner;
    private boolean fork;
    private List<Branch> branches;

}
