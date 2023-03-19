package com.task.rest.api.config;

import com.task.rest.api.exception.InvalidInputException;
import com.task.rest.api.exception.NoPrimeListException;
import com.task.rest.api.model.ApiResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class GlobalApiExceptionHandler {

    @ExceptionHandler(InvalidInputException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseEntity<ApiResponse> handleInvalidInput(
            InvalidInputException exception
    ) {

        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(ApiResponse.builder().errorMessage(exception.getMessage()).build());
    }

    @ExceptionHandler(NoPrimeListException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ResponseEntity<ApiResponse> handleEmptyList(
            NoPrimeListException exception
    ) {

        return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .body(ApiResponse.builder().errorMessage(exception.getMessage()).build());
    }

}
