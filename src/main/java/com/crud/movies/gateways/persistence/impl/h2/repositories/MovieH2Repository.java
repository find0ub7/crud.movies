package com.crud.movies.gateways.persistence.impl.h2.repositories;

import com.crud.movies.gateways.persistence.impl.h2.entities.MovieEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MovieH2Repository extends JpaRepository<MovieEntity, Long> {

}
