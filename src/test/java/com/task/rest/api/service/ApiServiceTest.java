package com.task.rest.api.service;

import com.github.benmanes.caffeine.cache.Cache;
import com.task.rest.api.exception.InvalidInputException;
import com.task.rest.api.exception.NoPrimeListException;
import lombok.SneakyThrows;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.cache.CacheManager;
import org.springframework.cache.caffeine.CaffeineCache;
import org.springframework.cache.caffeine.CaffeineCacheManager;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;

import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@RunWith(MockitoJUnitRunner.class)
public class ApiServiceTest {

    @InjectMocks
    private ApiService service;

    private CacheManager cacheManager = new CaffeineCacheManager("cacheList");

    @SneakyThrows
    @Test
    public void itShouldFindPrimeNumbersBetter_1(){
        List<Integer> response = service.findPrimeNumbersBetter("10");
        assertEquals(response,Arrays.asList(2,3,5,7));
    }

    @SneakyThrows
    @Test
    public void itShouldFindPrimeNumbersBetter_2(){
        List<Integer> response = service.findPrimeNumbersBetter("13");
        assertEquals(response,Arrays.asList(2,3,5,7,11,13));
    }

    @Test
    public void itShouldThrowException_Null() {
        Exception thrown = Assertions.assertThrows(InvalidInputException.class, () -> service.findPrimeNumbersBetter(null),"Your input is invalid");
        assertTrue(thrown.getMessage().contentEquals("Your input is invalid"));
    }


    @Test
    public void itShouldThrowException_SpecialCharacters() {
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
    public void itShouldThrowException_SpecialCharacters_2() {
        Exception thrown = Assertions.assertThrows(InvalidInputException.class, () -> service.findPrimeNumbersSlow("   1"),"Your input is invalid");
        assertTrue(thrown.getMessage().contentEquals("Your input is invalid"));
    }

    @Test
    public void itShouldThrowException_Zero() {
        Exception thrown = Assertions.assertThrows(NoPrimeListException.class, () -> service.findPrimeNumbersBetter("0"),"No Prime Numbers found for your input");
        assertTrue(thrown.getMessage().contentEquals("No Prime Numbers found for your input"));
    }

    @Test
    public void itShouldThrowException_One() {
        Exception thrown = Assertions.assertThrows(NoPrimeListException.class, () -> service.findPrimeNumbersSlow("1"),"No Prime Numbers found for your input");
        assertTrue(thrown.getMessage().contentEquals("No Prime Numbers found for your input"));
    }

    @Test
    public void itShouldThrowException_NegativeInput() {
        Exception thrown = Assertions.assertThrows(NoPrimeListException.class, () -> service.findPrimeNumbersBetter("-240"),"No Prime Numbers found for your input");
        assertTrue(thrown.getMessage().contentEquals("No Prime Numbers found for your input"));
    }

    @Test
    public void itShouldThrowException_DecimalInput() {
        Exception thrown = Assertions.assertThrows(InvalidInputException.class, () -> service.findPrimeNumbersBetter("1.2"),"Your input is invalid");
        assertTrue(thrown.getMessage().contentEquals("Your input is invalid"));
    }

    @SneakyThrows
    @Test
    public void testCache(){
        CaffeineCache caffeineCache = (CaffeineCache) cacheManager.getCache("cacheList");
        Cache<Object, Object> nativeCache = Objects.requireNonNull(caffeineCache).getNativeCache();
        cacheManager.getCache("cacheList").put("3",Arrays.asList(2,3));

        List<Integer> response = service.findPrimeNumbersSlow("3");
        assertEquals(response,nativeCache.asMap().get("3"));
    }


}