package com.crud.movies.usecases.validators;

import com.crud.movies.domains.Movie;
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

  public List<String> validate(Movie movie) {
    List<String> validationErrors = new ArrayList<>();
    if (!moviePersistenceGateway.existsById(movie.getId())) {
      validationErrors.add("Filme nao existe");
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
      validationErrors.addAll(actorValidator.validate(movie.getCasting()));
    }
    return validationErrors;
  }
}
