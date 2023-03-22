package com.task.rest.api.service;

import com.task.rest.api.exception.InvalidInputException;
import com.task.rest.api.exception.NoPrimeListException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ApiService{

    private final ServiceAlgo_1 service1;
    private final ServiceAlgo_2 service2;

    public List<Integer> findPrimeNumbers_func1(String num)throws InvalidInputException, NoPrimeListException {
        return service1.findPrimeNumbers(num);
    }

    public List<Integer> findPrimeNumbers_func2(String num)throws InvalidInputException, NoPrimeListException {
        return service2.findPrimeNumbers(num);
    }

}
