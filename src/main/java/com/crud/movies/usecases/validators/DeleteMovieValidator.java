package com.crud.movies.usecases.validators;

import com.crud.movies.domains.Movie;
import com.crud.movies.gateways.persistence.MoviePersistenceGateway;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;

@Component
@RequiredArgsConstructor
public class DeleteMovieValidator {

  private final MoviePersistenceGateway moviePersistenceGateway;

  public List<String> validate(Movie movie) {
    boolean movieExists = moviePersistenceGateway.existsById(movie.getId());
    List<String> validationErrors = new ArrayList<>();

    if (movie.getId() == null) {
      validationErrors.add("Filme sem id");
    }

    if (!movieExists) {
      validationErrors.add("Filme nao existe. Id=" + movie.getId());
    }
    return validationErrors;
  }
}
