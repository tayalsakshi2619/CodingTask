package com.task.rest.api.model;

import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class ApiResponse {

    private List<Integer> list;
    private String errorMessage;
}
