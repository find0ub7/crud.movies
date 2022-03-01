package com.crud.movies.gateways.persistence.impl.h2.entities;

import com.crud.movies.domains.Movie;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;
import java.util.stream.Collectors;

@Entity
@Table(name = "TB_MOVIES")
@NoArgsConstructor
public class MovieEntity {
    @Id @GeneratedValue
    private Long id;

    @Column(name = "TITLE")
    private String title;

    @Column(name = "GENRE")
    private String genre;

    @OneToMany(mappedBy = "movie", cascade = CascadeType.ALL)
    private List<ActorEntity> casting;

    public MovieEntity(Movie movie) {
        id = movie.getId();
        title = movie.getTitle();
        genre = movie.getGenre();
        casting = movie.getCasting().stream().map(actor -> new ActorEntity(actor, this)).collect(Collectors.toList());
    }

    public Movie toDomain() {
        return Movie.builder()
                .id(id)
                .title(title)
                .genre(genre)
                .casting(casting.stream().map(ActorEntity::toDomain).collect(Collectors.toList()))
                .build();
    }
}
