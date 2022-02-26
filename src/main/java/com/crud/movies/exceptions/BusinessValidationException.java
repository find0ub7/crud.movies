package com.crud.movies.exceptions;

import java.util.List;
import java.util.stream.Collectors;

public class BusinessValidationException extends RuntimeException {

  private final List<String> validationErrors;

  public BusinessValidationException(List<String> validationErrors) {
    super(validationErrors.stream().collect(Collectors.joining(";")));
    this.validationErrors = validationErrors;
  }

  public BusinessValidationException(String validationErrorMessage) {
    super(validationErrorMessage);
    validationErrors = List.of(validationErrorMessage);
  }
}
