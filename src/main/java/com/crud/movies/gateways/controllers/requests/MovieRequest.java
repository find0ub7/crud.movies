package com.crud.movies.gateways.controllers.requests;

import com.crud.movies.domains.Movie;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.stream.Collectors;

@Data
@NoArgsConstructor
public class MovieRequest {

  @ApiModelProperty(required = true, value = "Titulo do filme", example = "A Lagoa Azul")
  private String title;

  @ApiModelProperty(required = true, value = "Genero do filme", example = "Comedia")
  private String genre;

  @ApiModelProperty(
      required = true,
      value = "Atores do filme")
  private List<ActorRequest> casting;

  public Movie toDomain() {
    return Movie.builder()
            .title(title)
            .genre(genre)
            .casting(casting != null ? casting.stream().map(ActorRequest::toDomain).collect(Collectors.toList()) : null)
            .build();
  }
}
