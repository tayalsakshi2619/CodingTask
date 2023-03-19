package com.task.rest.api.controller;

import com.task.rest.api.exception.InvalidInputException;
import com.task.rest.api.model.ApiResponse;
import com.task.rest.api.service.ApiService;
import lombok.SneakyThrows;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Arrays;

import static org.junit.Assert.assertEquals;

@RunWith(MockitoJUnitRunner.class)
public class ApiControllerTest {

    @InjectMocks
    private ApiController controller;

    @Mock
    private ApiService service;

    @SneakyThrows
    @Test
    public void itShouldCalculateVersion1(){
        Mockito.when(service.findPrimeNumbersSlow("10")).thenReturn(Arrays.asList(2,3,5,7));
        ResponseEntity<ApiResponse> response = controller.calculateVersion1("10");
        assertEquals(HttpStatus.OK,response.getStatusCode());
        assertEquals(Arrays.asList(2,3,5,7),response.getBody().getList());
    }

    @SneakyThrows
    @Test
    public void itShouldCalculateVersion2() {
        Mockito.when(service.findPrimeNumbersBetter("13")).thenReturn(Arrays.asList(2,3,5,7,11,13));
        ResponseEntity<ApiResponse> response = controller.calculateVersion2("13");
        assertEquals(HttpStatus.OK,response.getStatusCode());
        assertEquals(Arrays.asList(2,3,5,7,11,13),response.getBody().getList());
    }
}