package com.task.rest.api.service;

import com.task.rest.api.exception.InvalidInputException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@Slf4j
public class ApiService implements ServiceInterface {

    //Time Complexity: O(sqrt(n))

    @Cacheable("cacheList")
    public List<Integer> findPrimeNumbersBetter(String num) throws InvalidInputException {
     try{
         int n = Integer.parseInt(num);
        List<Integer> list = new ArrayList<>();
        int i = 2;
        while(i<=n){
            if(isPrimeBetter(i))
                list.add(i);
            i++;
        }

        return list;}
     catch(NumberFormatException e){
         log.error("Encountered exception while parsing number");
         throw new InvalidInputException("Your input is invalid");
     }
    }

    private boolean isPrimeBetter(int n){
        if(n==2 || n==3)
            return true;
        else if(n<=1 || n%2==0 || n%3==0)
            return false;
        else{
            for(int i = 5;i<=Math.sqrt(n);i=i+6){
                if(n%i==0 || n%(i+2)==0)
                    return false;
            }
            return true;
        }

    }

    //Time Complexity: O(sqrt(n))

    @Cacheable("cacheList")
    public List<Integer> findPrimeNumbersSlow(String num) throws InvalidInputException {
        try{
            int n = Integer.parseInt(num);
            List<Integer> list = new ArrayList<>();
            int i = 2;
            while(i<=n){
                if(isPrimeSlow(i))
                    list.add(i);
                i++;
            }
            return list;}
        catch(NumberFormatException e){
            log.error("Encountered exception while parsing number");
            throw new InvalidInputException("Your input is invalid");
        }
    }

    private boolean isPrimeSlow(int n){
        if(n<=1)
            return false;
        for(int i =2;i<=Math.sqrt(n);i++){
            if(n%i==0)
                return false;
        }
        return true;
    }


}
