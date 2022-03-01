package com.crud.movies.usecases.validators;

import com.crud.movies.domains.Movie;
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

  public List<String> validate(Movie movie) {
    List<String> validationErrors = new ArrayList<>();

    if (movie == null) return List.of("Movie nao pode ser nulo");

    if (movie.getId() != null) {
      validationErrors.add("Filme ja cadastrado. Id=" + movie.getId());
    }

    if (!StringUtils.hasText(movie.getTitle())) {
      validationErrors.add("Titulo eh obrigatorio");
    }

    if (!StringUtils.hasText(movie.getGenre())) {
      validationErrors.add("Genero eh obrigatorio");
    }

    if (CollectionUtils.isEmpty(movie.getCasting())) {
      validationErrors.add("Elenco eh obrigatorio");
    } else {
      List<String> castingValidationErrors = actorValidator.validate(movie.getCasting());
      validationErrors.addAll(castingValidationErrors);
    }

    return validationErrors;
  }
}
