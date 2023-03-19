package com.task.rest.api.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ApiResponse {

    @JsonProperty("Initial")
    private String number;

    @JsonProperty("Primes")
    private List<Integer> list;

    @JsonProperty("ErrorMessage")
    private String errorMessage;
}
