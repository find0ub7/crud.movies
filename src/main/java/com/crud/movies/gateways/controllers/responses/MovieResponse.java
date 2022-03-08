package com.crud.movies.gateways.controllers.responses;

import com.crud.movies.domains.Movie;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;
import java.util.stream.Collectors;

@Data
@XmlRootElement
@NoArgsConstructor
public class MovieResponse {

    private Long id;
    private String title;
    private String genre;
    private List<ActorResponse> casting;

    public MovieResponse(Movie movie) {
        id = movie.getId();
        title = movie.getTitle();
        genre = movie.getGenre();
        casting = movie.getCasting().stream().map(ActorResponse::new).collect(Collectors.toList());
    }
}
