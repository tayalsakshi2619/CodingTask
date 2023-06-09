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
public class ServiceAlgoTest2 {
    @InjectMocks
    private ServiceAlgo_2 service;

    private final CacheManager cacheManager = new CaffeineCacheManager("cacheList");

    @SneakyThrows
    @Test
    public void itShouldFindPrimeNumbersFunc(){
        List<Integer> response = service.findPrimeNumbers("10");
        assertEquals(response, Arrays.asList(2,3,5,7));
    }

    @SneakyThrows
    @Test
    public void itShouldFindPrimeNumbersFunc_2(){
        List<Integer> response = service.findPrimeNumbers("13");
        assertEquals(response,Arrays.asList(2,3,5,7,11,13));
    }

    @Test
    public void itShouldThrowException_Null() {
        Exception thrown = Assertions.assertThrows(InvalidInputException.class, () -> service.findPrimeNumbers(null),"Your input is invalid");
        assertTrue(thrown.getMessage().contentEquals("Your input is invalid :null"));
    }

    @Test
    public void itShouldThrowException_SpecialCharacters_2() {
        Exception thrown = Assertions.assertThrows(InvalidInputException.class, () -> service.findPrimeNumbers("   1"),"Your input is invalid");
        assertTrue(thrown.getMessage().contentEquals("Your input is invalid :   1"));
    }

    @Test
    public void itShouldThrowException_Zero() {
        Exception thrown = Assertions.assertThrows(NoPrimeListException.class, () -> service.findPrimeNumbers("0"),"No Prime Numbers found for your input");
        assertTrue(thrown.getMessage().contentEquals("No Prime Numbers found for your input :0"));
    }

    @Test
    public void itShouldThrowException_One() {
        Exception thrown = Assertions.assertThrows(NoPrimeListException.class, () -> service.findPrimeNumbers("1"),"No Prime Numbers found for your input");
        assertTrue(thrown.getMessage().contentEquals("No Prime Numbers found for your input :1"));
    }

    @Test
    public void itShouldThrowException_NegativeInput() {
        Exception thrown = Assertions.assertThrows(NoPrimeListException.class, () -> service.findPrimeNumbers("-240"),"No Prime Numbers found for your input");
        assertTrue(thrown.getMessage().contentEquals("No Prime Numbers found for your input :-240"));
    }

    @Test
    public void itShouldThrowException_DecimalInput() {
        Exception thrown = Assertions.assertThrows(InvalidInputException.class, () -> service.findPrimeNumbers("1.2"),"Your input is invalid");
        assertTrue(thrown.getMessage().contentEquals("Your input is invalid :1.2"));
    }

    @SneakyThrows
    @Test
    public void testCache(){
        CaffeineCache caffeineCache = (CaffeineCache) cacheManager.getCache("cacheList");
        Cache<Object, Object> nativeCache = Objects.requireNonNull(caffeineCache).getNativeCache();
        cacheManager.getCache("cacheList").put("3",Arrays.asList(2,3));

        List<Integer> response = service.findPrimeNumbers("3");
        assertEquals(response,nativeCache.asMap().get("3"));
    }


}