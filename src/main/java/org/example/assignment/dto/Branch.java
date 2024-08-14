package org.example.assignment.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Branch {

    @JsonProperty("name")
    private String name;
    private Commit commit;
}
