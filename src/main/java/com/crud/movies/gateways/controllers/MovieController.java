package com.crud.movies.gateways.controllers;

import com.crud.movies.domains.Movie;
import com.crud.movies.gateways.controllers.requests.MovieRequest;
import com.crud.movies.gateways.controllers.responses.MovieResponse;
import com.crud.movies.usecases.*;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/movies")
@RequiredArgsConstructor
public class MovieController {

  private final CreateMovie createMovie;
  private final UpdateMovie updateMovie;
  private final FindMovieById findMovieById;
  private final ListMovies listMovies;
  private final DeleteMovie deleteMovie;

  @PostMapping(
      consumes = MediaType.APPLICATION_JSON_VALUE,
      produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.TEXT_XML_VALUE})
  @ResponseStatus(HttpStatus.CREATED)
  public MovieResponse createMovie(@RequestBody MovieRequest movieRequest) {
    Movie movie = movieRequest.toDomain();
    Movie movieSaved = createMovie.execute(movie);
    return new MovieResponse(movieSaved);
  }

  @PutMapping(path = "/{id}",
          consumes = MediaType.APPLICATION_JSON_VALUE,
          produces = MediaType.APPLICATION_JSON_VALUE)
  @ResponseStatus(HttpStatus.OK)
  public MovieResponse updateMovie(@PathVariable("id") Long id, @RequestBody MovieRequest movieRequest) {
    Movie movie = movieRequest.toDomain();
    movie.setId(id);
    Movie movieSaved = updateMovie.execute(movie);
    return new MovieResponse(movieSaved);
  }

  @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
  @ResponseStatus(HttpStatus.OK)
  public List<MovieResponse> listMovies() {
    List<Movie> movies = listMovies.execute();
    return movies.stream().map(MovieResponse::new).collect(Collectors.toList());
  }

  // GET /movies/{id}

  // DEL /movies/{id}
}
