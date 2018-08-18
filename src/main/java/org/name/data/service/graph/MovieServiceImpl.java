package org.name.data.service.graph;

import org.name.data.DataAccessManager;
import org.name.model.domain.movie.Actor;
import org.name.model.domain.movie.Movie;
import org.neo4j.ogm.session.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.Set;

@Component
public class MovieServiceImpl extends GraphService<Movie> implements MovieService {

  /**
   * Constructor for testing to inject the session programmatically. In this way we can perform
   * tests on a clean database.
   *
   * @param session
   */
  public MovieServiceImpl(Session session) {
    super(session);
  }

  /** The no-args constructor is autowired. */
  @Autowired
  public MovieServiceImpl(DataAccessManager dataAccessManager) {
    super(dataAccessManager.getNeo4JSession());
  }

  @Override
  public Class<Movie> getEntityType() {
    return Movie.class;
  }

  @Override
  public String accessName() {
    return null;
  }

  @Override
  public Iterable<Movie> readMovieByTitle(String title) {
    String query = "MATCH (m:Movie {title: \'" + title + "\'})<-[:ACTS_IN]-(a:Actor) RETURN m, a";
    return session.query(Movie.class, query, Collections.emptyMap());
  }

  @Override
  public void deleteMovieByTitle(String title) {}

  @Override
  public void addActors(Movie movie, Set<Actor> actors) {}
}
