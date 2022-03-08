package com.crud.movies.gateways.controllers.handlers;

import com.crud.movies.exceptions.BusinessValidationException;
import com.crud.movies.gateways.controllers.responses.ErrorResponse;
import lombok.val;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.stream.Collectors;

@RestControllerAdvice
public class MovieControllerAdvice {

    @ExceptionHandler
    public ResponseEntity<ErrorResponse> handleBusinesseValidation(BusinessValidationException exception) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ErrorResponse(exception.getValidationErrors().toString()));
    }

    @ExceptionHandler
    public ResponseEntity<ErrorResponse> handleMethodArgumentNotValid(MethodArgumentNotValidException exception) {
        val errorMessages = exception.getFieldErrors()
                .stream()
                .map(fieldError -> mapToErrorMessage(fieldError))
                .collect(Collectors.toList());

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ErrorResponse(errorMessages.toString()));
    }

    private String mapToErrorMessage(FieldError fieldError) {
        return fieldError.getField() + ": " + fieldError.getDefaultMessage();
    }
}
