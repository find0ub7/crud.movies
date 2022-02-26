package com.crud.movies.domains;

import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class Movie {

  private String id;
  private String title;
  private String genre;
  private List<Actor> casting;
}
