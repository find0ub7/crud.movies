package com.crud.movies.gateways.persistence.impl.h2.entities;

import com.crud.movies.domains.Actor;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name = "TB_ACTORS")
@NoArgsConstructor
public class ActorEntity {

  @Id @GeneratedValue private Long id;

  @Column(name = "NAME")
  private String name;

  @ManyToOne
  @JoinColumn(name = "ID_MOVIE", referencedColumnName = "ID")
  private MovieEntity movie;

  public ActorEntity(Actor actor, MovieEntity movie) {
    id = actor.getId();
    name = actor.getName();
    this.movie = movie;
  }

  public Actor toDomain() {
    return Actor.builder().id(id).name(name).build();
  }
}
