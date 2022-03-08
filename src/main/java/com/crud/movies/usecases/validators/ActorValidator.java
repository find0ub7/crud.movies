package com.crud.movies.usecases.validators;

import com.crud.movies.domains.Actor;
import com.crud.movies.domains.MessageCode;
import com.crud.movies.domains.ValidationError;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Component
public class ActorValidator {

  public List<ValidationError> validate(List<Actor> casting) {
    return casting.stream().flatMap(this::validate).collect(Collectors.toList());
  }

  public Stream<ValidationError> validate(Actor actor) {
    List<ValidationError> validationErrors = new ArrayList<>();
    if (!StringUtils.hasText(actor.getName())) {
      validationErrors.add(
          ValidationError.builder().keyMessage(MessageCode.REQUIRED_FIELD)
                  .params(List.of(MessageCode.CASTING_NAME))
                  .build());
    }

    return validationErrors.stream();
  }
}
