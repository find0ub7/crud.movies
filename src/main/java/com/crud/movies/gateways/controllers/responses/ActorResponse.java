package com.crud.movies.gateways.controllers.responses;

import com.crud.movies.domains.Actor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class ActorResponse {

    private Long id;
    private String name;

    public ActorResponse(Actor actor) {
        id = actor.getId();
        name = actor.getName();
    }
}
