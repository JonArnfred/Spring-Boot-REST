package integration.api;

import integration.IntegrationTest;
import org.junit.Test;
import org.name.api.MovieAPI;
import org.name.model.JsonResponse;
import org.name.model.domain.movie.Movie;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.Assert;

public class MovieAPITest extends IntegrationTest {

  @Autowired private MovieAPI movieAPI;

  @Test
  public void readAll() {

    JsonResponse<Iterable<Movie>> response = movieAPI.readAll();
    Iterable<Movie> movies = response.getData();
    int size = 0;
    for (Movie m : movies) {
      size++;
    }

    Assert.isTrue(size == 4, "There are 4 movies in the database");
  }
}
