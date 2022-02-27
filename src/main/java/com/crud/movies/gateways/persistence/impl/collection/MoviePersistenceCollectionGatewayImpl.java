package com.crud.movies.gateways.persistence.impl.collection;

import com.crud.movies.domains.Movie;
import com.crud.movies.gateways.persistence.MoviePersistenceGateway;
import org.springframework.util.StringUtils;

import java.util.*;

public class MoviePersistenceCollectionGatewayImpl implements MoviePersistenceGateway {

  private static final Map<String, Movie> movies = new HashMap<>();

  @Override
  public Movie save(Movie movie) {
    if (!StringUtils.hasText(movie.getId())) {
      movie.setId(UUID.randomUUID().toString());
    }

    movie.getCasting().stream()
        .filter(actor -> !StringUtils.hasText(actor.getId()))
        .forEach(actor -> actor.setId(UUID.randomUUID().toString()));

    movies.put(movie.getId(), movie);
    return movie;
  }

  @Override
  public boolean existsById(String id) {
    return movies.containsKey(id);
  }

  @Override
  public void delete(Movie movie) {
    movies.remove(movie.getId());
  }

  @Override
  public List<Movie> findAll() {
    return new ArrayList<>(movies.values());
  }

  @Override
  public Optional<Movie> findById(String movieId) {
    return Optional.ofNullable(movies.get(movieId));
  }
}
