package org.name.api;

import org.name.data.service.graph.MovieServiceImpl;
import org.name.exceptions.AppException;
import org.name.model.JsonResponse;
import org.name.model.domain.movie.Movie;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/movies")
public class MovieAPI {

  @Autowired private MovieServiceImpl movieService;

  @RequestMapping(
    value = "/read-all",
    method = RequestMethod.GET,
    produces = "application/json;charset=UTF-8"
  )
  public JsonResponse readAll() {
    try {
      Iterable<Movie> movies = movieService.readAll();
      return new JsonResponse(movies);
    } catch (AppException e) {
      // TODO: handle exception properly
      return new JsonResponse(e);
    }
  }
}
