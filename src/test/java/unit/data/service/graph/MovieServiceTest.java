package unit.data.service.graph;

import org.junit.Before;
import org.junit.Test;
import org.name.data.service.graph.MovieServiceImpl;
import org.name.exceptions.AppException;
import org.name.model.domain.movie.Actor;
import org.name.model.domain.movie.Movie;
import org.neo4j.ogm.config.Configuration;
import org.neo4j.ogm.session.Session;
import org.neo4j.ogm.session.SessionFactory;
import org.springframework.util.Assert;

import java.util.HashSet;
import java.util.Set;

public class MovieServiceTest extends GraphServiceTest {

  private MovieServiceImpl movieServiceImpl;

  /**
   * Setup an OGM session which does not require Spring Boot configuration to run. This is useful
   * for merely testing the service layer.
   */
  @Before
  public void setUp() {

    Configuration configuration =
        new Configuration.Builder()
            .uri(neoServer.boltURI().toString())
            // .uri(neoServer.httpURI().toString()) // For HTTP
            // .uri(new File("target/graph.db").toURI().toString()) // For Embedded
            .build();

    SessionFactory sessionFactory =
        new SessionFactory(configuration, "org.name.model.domain.movie");
    Session session = sessionFactory.openSession();
    movieServiceImpl = new MovieServiceImpl(session);
  }

  // CREATE
  @Test
  public void createMovie_should_create_movie() throws AppException {

    // act
    Movie movie = new Movie("Indiana Jones");
    Set<Actor> actors = new HashSet<>();
    actors.add(new Actor("Joe P"));
    movie.setActors(actors);

    movieServiceImpl.createOrUpdate(movie);
    Iterable<Movie> movies = movieServiceImpl.readMovieByTitle("Indiana Jones");

    // assert
    Assert.isTrue(movies.iterator().hasNext(), "The Iterable must have a non-null value");
  }

  private void insertMove() throws AppException {
    Movie movie = new Movie("Indiana Jones");
    Set<Actor> actors = new HashSet<>();
    actors.add(new Actor("Joe P"));
    movie.setActors(actors);

    movieServiceImpl.createOrUpdate(movie);
  }

  // READ ALL
  @Test
  public void readAllMovies_should_read_correctly() throws AppException {
    Iterable<Movie> movies = movieServiceImpl.readAll();
    int size = 0;
    for (Movie movie : movies) {
      size++;
    }
    Assert.isTrue(size == 3, "There should be 3 movies");
  }

  // READ SINGLE
  @Test
  public void readMovieByTitle_should_read_correctly() throws AppException {
    String title = "The Cry Baby Killer";
    Iterable<Movie> movies = movieServiceImpl.readMovieByTitle(title);
    int size = 0;
    for (Movie movie : movies) {
      size++;
    }
    Assert.isTrue(size == 1, "There should be 1 called " + title);
  }

  // UPDATE

  // DELETE

}
