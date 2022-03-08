package com.crud.movies.usecases;

import com.crud.movies.domains.MessageCode;
import com.crud.movies.domains.Movie;
import com.crud.movies.domains.ValidationError;
import com.crud.movies.exceptions.BusinessValidationException;
import com.crud.movies.gateways.persistence.MoviePersistenceGateway;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class FindMovieById {

  private final MoviePersistenceGateway moviePersistenceGateway;

  public Optional<Movie> execute(Long movieId) {
    if (movieId == null) {
      throw new BusinessValidationException(
          ValidationError.builder()
              .keyMessage(MessageCode.REQUIRED_FIELD)
              .params(List.of(MessageCode.MOVIE_ID_FIELD))
              .build());
    }
    return moviePersistenceGateway.findById(movieId);
  }
}
