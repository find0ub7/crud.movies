package com.crud.movies.domains;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Actor {

  private String id;
  private String name;
}
