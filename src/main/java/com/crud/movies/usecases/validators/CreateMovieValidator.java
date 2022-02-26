package com.crud.movies.usecases.validators;

import com.crud.movies.domains.Movie;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;

public class CreateMovieValidator {

  public List<String> validate(Movie movie) {
    List<String> validationErrors = new ArrayList<>();

    if (StringUtils.hasText(movie.getId())) {
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
    }
    return validationErrors;
  }
}
