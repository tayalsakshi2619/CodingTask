package com.task.rest.api.service;

import com.task.rest.api.exception.InvalidInputException;
import com.task.rest.api.exception.NoPrimeListException;

import java.util.List;

public interface ServiceInterface {

    List<Integer> findPrimeNumbers(String num) throws InvalidInputException, NoPrimeListException;
}
