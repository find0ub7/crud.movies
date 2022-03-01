package com.crud.movies.gateways.persistence.impl.h2;

import com.crud.movies.domains.Movie;
import com.crud.movies.gateways.persistence.MoviePersistenceGateway;
import com.crud.movies.gateways.persistence.impl.h2.entities.MovieEntity;
import com.crud.movies.gateways.persistence.impl.h2.repositories.MovieH2Repository;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Profile("prod")
@Component
@RequiredArgsConstructor
public class MoviePersistenceH2GatewayImpl implements MoviePersistenceGateway {

    private final MovieH2Repository movieH2Repository;

    @Override
    public Movie save(Movie movie) {
        MovieEntity movieEntity = movieH2Repository.save(new MovieEntity(movie));
        return movieEntity.toDomain();
    }

    @Override
    public boolean existsById(Long id) {
        return false;
    }

    @Override
    public void delete(Movie movie) {

    }

    @Override
    public List<Movie> findAll() {
        return movieH2Repository.findAll().stream().map(MovieEntity::toDomain).collect(Collectors.toList());
    }

    @Override
    public Optional<Movie> findById(Long movieId) {
        return Optional.empty();
    }
}
