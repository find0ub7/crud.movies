package com.crud.movies.usecases.validators;

import com.crud.movies.domains.MessageCode;
import com.crud.movies.domains.Movie;
import com.crud.movies.domains.ValidationError;
import com.crud.movies.gateways.persistence.MoviePersistenceGateway;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
@RequiredArgsConstructor
public class DeleteMovieValidator {

  private final MoviePersistenceGateway moviePersistenceGateway;

  public List<ValidationError> validate(Movie movie) {
    boolean movieExists = moviePersistenceGateway.existsById(movie.getId());
    List<ValidationError> validationErrors = new ArrayList<>();

    if (movie.getId() == null) {
      validationErrors.add(
              ValidationError.builder()
                      .keyMessage(MessageCode.REQUIRED_FIELD)
                      .params(List.of(MessageCode.MOVIE_ID_FIELD))
                      .build());
    }

    if (!movieExists) {
      validationErrors.add(
              ValidationError.builder()
                      .keyMessage(MessageCode.RESOURCE_NOT_FOUND)
                      .params(List.of(MessageCode.MOVIE_FIELD, movie.getId()))
                      .build());
    }

    //recurso nao encontrado
    return validationErrors;
  }
}
