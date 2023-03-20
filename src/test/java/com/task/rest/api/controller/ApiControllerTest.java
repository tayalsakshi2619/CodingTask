package com.task.rest.api.controller;

import com.task.rest.api.model.ApiResponse;
import com.task.rest.api.service.ApiService;
import lombok.SneakyThrows;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Arrays;
import java.util.Objects;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class ApiControllerTest {

    @InjectMocks
    private ApiController controller;

    @Mock
    private ApiService service;

    @SneakyThrows
    @Test
    public void itShouldCalculateVersion1(){
        when(service.findPrimeNumbers("10")).thenReturn(Arrays.asList(2,3,5,7));
        ResponseEntity<ApiResponse> response = controller.calculateVersion1("10");
        assertEquals(HttpStatus.OK,response.getStatusCode());
        assertEquals(Arrays.asList(2,3,5,7), Objects.requireNonNull(response.getBody()).getList());
    }
}