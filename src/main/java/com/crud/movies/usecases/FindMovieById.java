package com.crud.movies.usecases;

import com.crud.movies.domains.Movie;
import com.crud.movies.exceptions.BusinessValidationException;
import com.crud.movies.gateways.persistence.MoviePersistenceGateway;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class FindMovieById {

  private final MoviePersistenceGateway moviePersistenceGateway;

  public Optional<Movie> execute(Long movieId) {
    if (movieId == null) {
      throw new BusinessValidationException("id é obrigatorio");
    }
    return moviePersistenceGateway.findById(movieId);
  }
}
