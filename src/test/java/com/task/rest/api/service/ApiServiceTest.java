package com.task.rest.api.service;

import lombok.SneakyThrows;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class ApiServiceTest {

    @InjectMocks
    private ApiService service;

    @Mock
    private ServiceAlgo1 service1;

    @Mock
    private ServiceAlgo2 service2;

    @SneakyThrows
    @Test
    public void itShouldInvokeFunc_1() {
        when(service1.findPrimeNumbers("3")).thenReturn(Arrays.asList(2,3));
        List<Integer> response = service.findPrimeNumbers_func1("3");
        assertEquals(Arrays.asList(2,3),response);
    }

    @SneakyThrows
    @Test
    public void itShouldInvokeFunc_2() {
        when(service2.findPrimeNumbers("3")).thenReturn(Arrays.asList(2,3));
        List<Integer> response = service.findPrimeNumbers_func2("3");
        assertEquals(Arrays.asList(2,3),response);
    }

}