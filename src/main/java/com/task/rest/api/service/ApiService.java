package com.task.rest.api.service;

import com.task.rest.api.exception.InvalidInputException;
import com.task.rest.api.exception.NoPrimeListException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Service
@Slf4j
public class ApiService implements ServiceInterface {

    @Cacheable("cacheList")
    public List<Integer> findPrimeNumbersBetter(String num) throws InvalidInputException, NoPrimeListException {
     try{
         int n = Integer.parseInt(num);
         if(n<=1) {
             log.info("Prime Number list is empty");
             throw new NoPrimeListException("No Prime Numbers found for your input :"+num);
         }
         else
             return IntStream.rangeClosed(2, n).boxed().toList().parallelStream().filter(this::isPrimeBetter).collect(Collectors.toList());
     }
     catch(NumberFormatException e){
         log.error("Encountered an exception while parsing number");
         throw new InvalidInputException("Your input is invalid :"+num);
     }
    }

    private boolean isPrimeBetter(int n){
        if(n==2 || n==3)
            return true;
        else if(n%2==0 || n%3==0)
            return false;
        else{
            for(int i = 5;i<=Math.sqrt(n);i=i+6){
                if(n%i==0 || n%(i+2)==0)
                    return false;
            }
            return true;
        }

    }


    @Cacheable("cacheList")
    public List<Integer> findPrimeNumbersSlow(String num) throws InvalidInputException, NoPrimeListException {
        try{
            int n = Integer.parseInt(num);
            if(n<=1) {
                log.info("Prime Number list is empty");
                throw new NoPrimeListException("No Prime Numbers found for your input :"+num);
            }
            else
                return IntStream.rangeClosed(2, n).boxed().toList().stream().filter(this::isPrimeSlow).collect(Collectors.toList());
        }
        catch(NumberFormatException e){
            log.error("Encountered an exception while parsing number");
            throw new InvalidInputException("Your input is invalid :"+num);
        }
    }

    private boolean isPrimeSlow(int n){
        for(int i =2;i<=Math.sqrt(n);i++){
            if(n%i==0)
                return false;
        }
        return true;
    }


}
