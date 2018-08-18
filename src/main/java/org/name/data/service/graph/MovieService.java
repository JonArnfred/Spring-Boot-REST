package org.name.data.service.graph;

import org.name.model.domain.movie.Actor;
import org.name.model.domain.movie.Movie;

import java.util.Set;

public interface MovieService {

  void addActors(Movie movie, Set<Actor> actors);

  Iterable<Movie> readMovieByTitle(String title);

  void deleteMovieByTitle(String title);
}
