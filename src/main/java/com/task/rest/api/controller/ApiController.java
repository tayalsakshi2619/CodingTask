package com.task.rest.api.controller;

import com.github.benmanes.caffeine.cache.Cache;

import com.task.rest.api.exception.InvalidInputException;
import com.task.rest.api.exception.NoPrimeListException;
import com.task.rest.api.model.ApiResponse;
import com.task.rest.api.service.ApiService;
import lombok.RequiredArgsConstructor;

import org.springframework.cache.CacheManager;
import org.springframework.cache.caffeine.CaffeineCache;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.Objects;

@RestController
@RequestMapping("/primes")
@RequiredArgsConstructor
public class ApiController {

    private final ApiService service;
    private final CacheManager cacheManager;

    //To handle first algorithm
    @GetMapping(value = "/v1/{num}", produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
    public ResponseEntity<ApiResponse> calculateVersion1(@PathVariable String num) throws InvalidInputException, NoPrimeListException {
        List<Integer> listOfPrimes = service.findPrimeNumbers_func1(num);
        return ResponseEntity.status(HttpStatus.OK).body(ApiResponse.builder().number(num).list(listOfPrimes).build());
    }

    //To handle second algorithm
    @GetMapping(value = "/v2/{num}", produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
    public ResponseEntity<ApiResponse> calculateVersion2(@PathVariable String num) throws InvalidInputException, NoPrimeListException {
        List<Integer> listOfPrimes = service.findPrimeNumbers_func2(num);
        return ResponseEntity.status(HttpStatus.OK).body(ApiResponse.builder().number(num).list(listOfPrimes).build());
    }

    //To enlist the values of cache : For Debugging
    @GetMapping(value = "/inspectCache/{cache_name}")
    public void inspectCache(@PathVariable("cache_name") String cacheName) {

        CaffeineCache caffeineCache = (CaffeineCache) cacheManager.getCache(cacheName);
        Cache<Object, Object> nativeCache = Objects.requireNonNull(caffeineCache).getNativeCache();
        for (Map.Entry<Object, Object> entry : nativeCache.asMap().entrySet()) {
            System.out.println(entry.getKey() + " : "+ entry.getValue());
        }
    }

}
