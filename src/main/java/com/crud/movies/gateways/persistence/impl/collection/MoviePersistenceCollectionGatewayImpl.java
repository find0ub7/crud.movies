package com.crud.movies.gateways.persistence.impl.collection;

import com.crud.movies.domains.Movie;
import com.crud.movies.gateways.persistence.MoviePersistenceGateway;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import java.util.*;

@Profile("!prod")
@Component("movie-persistence-collection")
public class MoviePersistenceCollectionGatewayImpl implements MoviePersistenceGateway {

  private static final Map<Long, Movie> movies = new HashMap<>();
  private static Long movieId = 1L;
  private static Long actorId = 1L;

  @Override
  public Movie save(Movie movie) {
    if (movie.getId() == null) {
      movie.setId(movieId++);
    }

    movie.getCasting().stream()
        .filter(actor -> actor.getId() == null)
        .forEach(actor -> actor.setId(actorId++));

    movies.put(movie.getId(), movie);
    return movie;
  }

  @Override
  public boolean existsById(Long id) {
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
  public Optional<Movie> findById(Long movieId) {
    return Optional.ofNullable(movies.get(movieId));
  }
}
