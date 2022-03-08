package com.crud.movies.gateways.controllers.requests;

import com.crud.movies.domains.Movie;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import java.util.List;
import java.util.stream.Collectors;

@Data
@NoArgsConstructor
public class MovieRequest {

  @NotBlank
  @ApiModelProperty(required = true, value = "Titulo do filme", example = "A Lagoa Azul")
  private String title;

  @NotBlank
  @ApiModelProperty(required = true, value = "Genero do filme", example = "Comedia")
  private String genre;

  @Valid
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
