package com.crud.movies.usecases;

import com.crud.movies.domains.Movie;
import com.crud.movies.exceptions.BusinessValidationException;
import com.crud.movies.gateways.persistence.MoviePersistenceGateway;
import com.crud.movies.usecases.validators.DeleteMovieValidator;
import lombok.RequiredArgsConstructor;

import java.util.List;

@RequiredArgsConstructor
public class DeleteMovie {

  private final MoviePersistenceGateway moviePersistenceGateway;
  private final DeleteMovieValidator deleteMovieValidator;

  public void execute(Movie movie) {
    List<String> validationErrors = deleteMovieValidator.validate(movie);

    if (!validationErrors.isEmpty()) {
      throw new BusinessValidationException(validationErrors);
    }

    moviePersistenceGateway.delete(movie);
  }
}