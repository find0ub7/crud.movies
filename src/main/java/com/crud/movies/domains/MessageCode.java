package com.crud.movies.domains;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum MessageCode {
  REQUIRED_FIELD("required.field"),
  MOVIE_FIELD("movie.field"),
  RESOURCE_ALREADY_EXISTS("resource.already.exists"),
  RESOURCE_NOT_FOUND("resource.not.found"),
  TITLE_FIELD("title.field"),
  GENRE_FIELD("genre.field"),
  CASTING_FIELD("casting.field"),
  CASTING_NAME("casting.name"),
  MOVIE_ID_FIELD("movie.id.field");

  private final String code;
}
