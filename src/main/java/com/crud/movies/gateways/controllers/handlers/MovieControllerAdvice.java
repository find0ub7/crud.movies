package com.crud.movies.gateways.controllers.handlers;

import com.crud.movies.domains.MessageCode;
import com.crud.movies.domains.ValidationError;
import com.crud.movies.exceptions.BusinessValidationException;
import com.crud.movies.gateways.controllers.responses.ErrorResponse;
import lombok.RequiredArgsConstructor;
import lombok.val;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.stream.Collectors;

@RestControllerAdvice
@RequiredArgsConstructor
public class MovieControllerAdvice {

    private final MessageSource messageSource;

    @ExceptionHandler
    public ResponseEntity<ErrorResponse> handleBusinesseValidation(BusinessValidationException exception) {
        val errorMessages = exception.getValidationErrors()
                .stream()
                .map(validationError -> mapToErrorMessage(validationError))
                .collect(Collectors.joining(";"));

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ErrorResponse(errorMessages));
    }

    @ExceptionHandler
    public ResponseEntity<ErrorResponse> handleMethodArgumentNotValid(MethodArgumentNotValidException exception) {
        val errorMessages = exception.getFieldErrors()
                .stream()
                .map(fieldError -> mapToErrorMessage(fieldError))
                .collect(Collectors.toList());

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ErrorResponse(errorMessages.toString()));
    }

    private String mapToErrorMessage(ValidationError validationError) {
        val messageCode = validationError.getKeyMessage();
        val params = validationError.getParams()
                .stream()
                .map(param -> translateMessage(param))
                .collect(Collectors.toList());

        return messageSource.getMessage(messageCode.getCode(), params.toArray(), LocaleContextHolder.getLocale());
    }

    private String translateMessage(Object param) {
        String key = param.toString();
        if (param instanceof MessageCode) {
            key = ((MessageCode) param).getCode();
        }
        return messageSource.getMessage(key, null, LocaleContextHolder.getLocale());
    }

    private String mapToErrorMessage(FieldError fieldError) {
        return fieldError.getField() + ": " + fieldError.getDefaultMessage();
    }
}
