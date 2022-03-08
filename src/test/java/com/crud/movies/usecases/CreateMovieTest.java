package com.crud.movies.usecases;

import com.crud.movies.domains.Movie;
import com.crud.movies.domains.ValidationError;
import com.crud.movies.exceptions.BusinessValidationException;
import com.crud.movies.gateways.persistence.MoviePersistenceGateway;
import com.crud.movies.usecases.validators.CreateMovieValidator;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoSettings;

import java.util.List;

@MockitoSettings
class CreateMovieTest {

  @InjectMocks CreateMovie createMovie;
  @Mock CreateMovieValidator createMovieValidator;
  @Mock MoviePersistenceGateway moviePersistenceGateway;

  @Test
  void shouldSaveWhenValidationSucceed() {
    Movie movie = Movie.builder().build();
    Mockito.when(createMovieValidator.validate(movie)).thenReturn(List.of());
    createMovie.execute(movie);
    Mockito.verify(moviePersistenceGateway).save(movie);
  }

  @Test
  void shouldNotSaveWhenValidationFailed() {
    Movie movie = Movie.builder().build();
    Mockito.when(createMovieValidator.validate(movie)).thenReturn(List.of(ValidationError.builder().build()));
    Assertions.assertThrows(BusinessValidationException.class, () -> createMovie.execute(movie));
  }
}
