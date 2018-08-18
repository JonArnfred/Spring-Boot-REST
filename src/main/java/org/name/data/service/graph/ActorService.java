package org.name.data.service.graph;

import org.name.data.service.Service;
import org.name.model.domain.movie.Actor;
import org.name.model.domain.movie.Movie;

import java.util.Set;

public interface ActorService extends Service<Actor> {

  void addMovies(Actor actor, Set<Movie> movies);
}
