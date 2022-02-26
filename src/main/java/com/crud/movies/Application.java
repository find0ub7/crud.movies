package com.crud.movies;

import com.crud.movies.usecases.ListMovies;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Application {

  public static void main(String[] args) {
    //    SpringApplication.run(Application.class, args);

    new ListMovies(new MoviePersistencePostgresGatewayImpl());
  }
}
