package com.crud.movies.usecases.validators;

import com.crud.movies.domains.MessageCode;
import com.crud.movies.domains.Movie;
import com.crud.movies.domains.ValidationError;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;

@Component
@RequiredArgsConstructor
public class CreateMovieValidator {

  private final ActorValidator actorValidator;

  public List<ValidationError> validate(Movie movie) {
    List<ValidationError> validationErrors = new ArrayList<>();

    if (movie == null)
      return List.of(
          ValidationError.builder()
              .keyMessage(MessageCode.REQUIRED_FIELD)
              .params(List.of(MessageCode.MOVIE_FIELD))
              .build());

    if (movie.getId() != null) {
      validationErrors.add(
              ValidationError.builder()
                      .keyMessage(MessageCode.RESOURCE_ALREADY_EXISTS)
                      .params(List.of(MessageCode.MOVIE_FIELD))
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
