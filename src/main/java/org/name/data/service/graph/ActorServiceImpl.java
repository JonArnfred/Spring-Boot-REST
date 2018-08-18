package org.name.data.service.graph;

import org.name.model.domain.movie.Actor;
import org.name.model.domain.movie.Movie;
import org.neo4j.ogm.session.Session;

import java.util.Set;

public class ActorServiceImpl extends GraphService<Actor> implements ActorService {

  public ActorServiceImpl(Session session) {
    super(session);
  }

  @Override
  public void addMovies(Actor actor, Set<Movie> movies) {}

  @Override
  public Class<Actor> getEntityType() {
    return Actor.class;
  }

  @Override
  public String accessName() {
    return "Graph backend";
  }
}
