package com.crud.movies.gateways.controllers.handlers;

import com.crud.movies.exceptions.BusinessValidationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class MovieControllerAdvice {

    @ExceptionHandler
    public ResponseEntity<String> handleBusinesseValidation(BusinessValidationException exception) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(exception.getValidationErrors().toString());
    }
}
