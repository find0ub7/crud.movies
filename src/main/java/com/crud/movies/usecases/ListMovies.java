package com.crud.movies.usecases;

import com.crud.movies.domains.Movie;
import com.crud.movies.gateways.persistence.MoviePersistenceGateway;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ListMovies {

  private final MoviePersistenceGateway moviePersistenceGateway;

  public List<Movie> execute() {
    return moviePersistenceGateway.findAll();
  }
}
