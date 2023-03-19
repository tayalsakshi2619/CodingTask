package com.task.rest.api.service;

import com.task.rest.api.exception.InvalidInputException;
import lombok.SneakyThrows;
import org.junit.Assert;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@RunWith(MockitoJUnitRunner.class)
public class ApiServiceTest {

    @InjectMocks
    private ApiService service;

    @SneakyThrows
    @Test
    public void itShouldFindPrimeNumbersBetter_1(){
        List<Integer> response = service.findPrimeNumbersBetter("10");
        assertEquals(response,Arrays.asList(2,3,5,7));
    }

    @Test
    public void itShouldFindPrimeNumbersBetter_2() throws InvalidInputException {
        List<Integer> response = service.findPrimeNumbersBetter("13");
        assertEquals(response,Arrays.asList(2,3,5,7,11,13));
    }

    @Test
    public void itShouldThrowException_1() {
        Exception thrown = Assertions.assertThrows(InvalidInputException.class, () -> service.findPrimeNumbersBetter(null),"Your input is invalid");
        assertTrue(thrown.getMessage().contentEquals("Your input is invalid"));
    }


    @Test
    public void itShouldThrowException_2() {
        Exception thrown = Assertions.assertThrows(InvalidInputException.class, () -> service.findPrimeNumbersBetter("! 2@3"),"Your input is invalid");
        assertTrue(thrown.getMessage().contentEquals("Your input is invalid"));
    }

    @SneakyThrows
    @Test
    public void itShouldFindPrimeNumbersSlow() {
        List<Integer> response = service.findPrimeNumbersSlow("3");
        assertEquals(response,Arrays.asList(2,3));
    }

    @Test
    public void itShouldThrowException_3() {
        Exception thrown = Assertions.assertThrows(InvalidInputException.class, () -> service.findPrimeNumbersBetter("   1"),"Your input is invalid");
        assertTrue(thrown.getMessage().contentEquals("Your input is invalid"));
    }
}