package com.crud.movies.usecases;

import com.crud.movies.domains.Movie;
import com.crud.movies.exceptions.BusinessValidationException;
import com.crud.movies.gateways.persistence.MoviePersistenceGateway;
import com.crud.movies.usecases.validators.UpdateMovieValidator;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class UpdateMovie {

  private final MoviePersistenceGateway moviePersistenceGateway;
  private final UpdateMovieValidator updateMovieValidator;

  public Movie execute(Movie movie) {
    List<String> validationErrors = updateMovieValidator.validate(movie);

    if (!validationErrors.isEmpty()) {
      throw new BusinessValidationException(validationErrors);
    }

    return moviePersistenceGateway.save(movie);
  }
}
