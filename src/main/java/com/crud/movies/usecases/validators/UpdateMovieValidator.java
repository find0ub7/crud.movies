package com.crud.movies.usecases.validators;

import com.crud.movies.domains.MessageCode;
import com.crud.movies.domains.Movie;
import com.crud.movies.domains.ValidationError;
import com.crud.movies.gateways.persistence.MoviePersistenceGateway;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;

@Component
@RequiredArgsConstructor
public class UpdateMovieValidator {

  private final ActorValidator actorValidator;
  private final MoviePersistenceGateway moviePersistenceGateway;

  public List<ValidationError> validate(Movie movie) {
    List<ValidationError> validationErrors = new ArrayList<>();
    if (!moviePersistenceGateway.existsById(movie.getId())) {
      validationErrors.add(
          ValidationError.builder()
              .keyMessage(MessageCode.RESOURCE_NOT_FOUND)
              .params(List.of(MessageCode.MOVIE_FIELD, movie.getId()))
              .build());
    }

    if (!StringUtils.hasText(movie.getTitle())) {
      validationErrors.add(
          ValidationError.builder()
              .keyMessage(MessageCode.REQUIRED_FIELD)
              .params(List.of(MessageCode.TITLE_FIELD))
              .build());
    }

    if (!StringUtils.hasText(movie.getGenre())) {
      validationErrors.add(
          ValidationError.builder()
              .keyMessage(MessageCode.REQUIRED_FIELD)
              .params(List.of(MessageCode.GENRE_FIELD))
              .build());
    }

    if (CollectionUtils.isEmpty(movie.getCasting())) {
      validationErrors.add(
          ValidationError.builder()
              .keyMessage(MessageCode.REQUIRED_FIELD)
              .params(List.of(MessageCode.CASTING_FIELD))
              .build());
    } else {
      List<ValidationError> castingValidationErrors = actorValidator.validate(movie.getCasting());
      validationErrors.addAll(castingValidationErrors);
    }
    return validationErrors;
  }
}
