package com.task.rest.api.service;

import com.task.rest.api.exception.InvalidInputException;
import com.task.rest.api.exception.NoPrimeListException;

import java.util.List;

public interface ServiceInterface {

    List<Integer> findPrimeNumbersBetter(String num) throws InvalidInputException, NoPrimeListException;
    List<Integer> findPrimeNumbersSlow(String num) throws InvalidInputException, NoPrimeListException;
}
