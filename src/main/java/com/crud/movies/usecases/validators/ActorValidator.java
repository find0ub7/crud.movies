package com.crud.movies.usecases.validators;

import com.crud.movies.domains.Actor;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class ActorValidator {

  public List<String> validate(List<Actor> casting) {
    return casting.stream().flatMap(this::validate).collect(Collectors.toList());
  }

  public Stream<String> validate(Actor actor) {
    List<String> validationErrors = new ArrayList<>();
    if (!StringUtils.hasText(actor.getName())) {
      validationErrors.add("O nome do/a ator/atriz eh obrigatorio");
    }

    return validationErrors.stream();
  }
}
