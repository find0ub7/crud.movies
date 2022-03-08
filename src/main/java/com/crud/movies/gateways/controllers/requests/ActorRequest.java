package com.crud.movies.gateways.controllers.requests;

import com.crud.movies.domains.Actor;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;

@Data
@NoArgsConstructor
public class ActorRequest {

    @ApiModelProperty(value = "Identificador do ator", example = "1")
    private Long id;

    @NotBlank
    @ApiModelProperty(required = true, value = "Nome do ator", example = "Fulano")
    private String name;

    public Actor toDomain() {
        return Actor.builder()
                .id(id)
                .name(name)
                .build();
    }
}
