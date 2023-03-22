package com.task.rest.api.service;

import com.task.rest.api.exception.InvalidInputException;
import com.task.rest.api.exception.NoPrimeListException;

import java.util.List;

public interface ServiceInterface {

    List<Integer> findPrimeNumbers_func1(String num)throws InvalidInputException, NoPrimeListException;
    List<Integer> findPrimeNumbers_func2(String num)throws InvalidInputException, NoPrimeListException;
}
