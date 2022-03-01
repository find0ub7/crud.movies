package com.crud.movies.gateways.persistence;

import com.crud.movies.domains.Movie;

import java.util.List;
import java.util.Optional;

public interface MoviePersistenceGateway {

  Movie save(Movie movie);

  boolean existsById(Long id);

  void delete(Movie movie);

  List<Movie> findAll();

  Optional<Movie> findById(Long movieId);
}
