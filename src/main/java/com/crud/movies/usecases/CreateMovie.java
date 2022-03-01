package com.crud.movies.usecases;

import com.crud.movies.domains.Movie;
import com.crud.movies.exceptions.BusinessValidationException;
import com.crud.movies.gateways.persistence.MoviePersistenceGateway;
import com.crud.movies.usecases.validators.CreateMovieValidator;
import lombok.RequiredArgsConstructor;
import lombok.val;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CreateMovie {

  private final CreateMovieValidator createMovieValidator;
  private final MoviePersistenceGateway moviePersistenceGateway;

  public Movie execute(Movie movie) {
    val validationErrors = createMovieValidator.validate(movie);

    if (!validationErrors.isEmpty()) {
      throw new BusinessValidationException(validationErrors);
    }

    return moviePersistenceGateway.save(movie);
  }
}
