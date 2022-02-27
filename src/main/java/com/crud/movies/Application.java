package com.crud.movies;

import com.crud.movies.domains.Actor;
import com.crud.movies.domains.Movie;
import com.crud.movies.gateways.persistence.MoviePersistenceGateway;
import com.crud.movies.gateways.persistence.impl.collection.MoviePersistenceCollectionGatewayImpl;
import com.crud.movies.usecases.*;
import com.crud.movies.usecases.validators.ActorValidator;
import com.crud.movies.usecases.validators.CreateMovieValidator;
import com.crud.movies.usecases.validators.DeleteMovieValidator;
import com.crud.movies.usecases.validators.UpdateMovieValidator;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.List;
import java.util.Optional;

@SpringBootApplication
public class Application {

  public static void main(String[] args) {
    //    SpringApplication.run(Application.class, args);

    ActorValidator actorValidator = new ActorValidator();
    CreateMovieValidator createMovieValidator = new CreateMovieValidator(actorValidator);
    MoviePersistenceGateway moviePersistenceGateway = new MoviePersistenceCollectionGatewayImpl();
    CreateMovie createMovie = new CreateMovie(createMovieValidator, moviePersistenceGateway);

    Movie movie =
        Movie.builder()
            .title("a lagoa azul")
            .genre("sessao da tarde")
            .casting(
                List.of(
                    Actor.builder().id("blablabla").name("Brooke Shields").build(),
                    Actor.builder().id("qualquercoisa").name("Christopher Atkins").build()))
            .build();

    createMovie.execute(movie);

    Movie movie2 =
        Movie.builder()
            .title("esqueceram de mim")
            .genre("sessao da tarde")
            .casting(List.of(Actor.builder().name("Macaulay Culkin").build()))
            .build();

    Movie movie2Saved = createMovie.execute(movie2);

    ListMovies listMovies = new ListMovies(moviePersistenceGateway);
    List<Movie> movies = listMovies.execute();
    movies.forEach(System.out::println);

    FindMovieById findMovieById = new FindMovieById(moviePersistenceGateway);
    Optional<Movie> movieFound = findMovieById.execute(movie2Saved.getId());
    //    System.out.println(movieFound.get());
    System.out.println("----");

    UpdateMovieValidator updateMovieValidator =
        new UpdateMovieValidator(actorValidator, moviePersistenceGateway);

    UpdateMovie updateMovie = new UpdateMovie(moviePersistenceGateway, updateMovieValidator);
    movie2.setTitle(movie2.getTitle() + " 2");
    //    movie2.setCasting(null);
    updateMovie.execute(movie2Saved);

    movies = listMovies.execute();
    movies.forEach(System.out::println);

    DeleteMovieValidator deleteMovieValidator = new DeleteMovieValidator(moviePersistenceGateway);
    DeleteMovie deleteMovie = new DeleteMovie(moviePersistenceGateway, deleteMovieValidator);
    deleteMovie.execute(movie);

    System.out.println("----");

    movies = listMovies.execute();
    movies.forEach(System.out::println);
  }
}
