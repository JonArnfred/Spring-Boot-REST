package org.name.model.domain.movie;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import org.neo4j.ogm.annotation.GeneratedValue;
import org.neo4j.ogm.annotation.Id;
import org.neo4j.ogm.annotation.NodeEntity;
import org.neo4j.ogm.annotation.Relationship;

import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
@NodeEntity(label = Lab.ACTOR)
public class Actor implements Entity {

  @Id
  @GeneratedValue(strategy = IdGenerator.class)
  private UUID uuid;

  @Id @GeneratedValue private Long id;

  private String name;

  @Relationship(type = Rel.ACTS_IN, direction = Relationship.OUTGOING)
  private Set<Movie> movies = new HashSet<>();

  public Actor() {}

  public Actor(String name, Set<Movie> movies) {
    this.name = name;
    this.movies = movies;
  }

  public Actor(String name) {
    this.name = name;
  }

  public Long getId() {
    return id;
  }

  @Override
  public UUID getUuid() {
    return uuid;
  }

  public String getName() {
    return name;
  }

  public Set<Movie> getMovies() {
    return movies;
  }

  public void setName(String name) {
    this.name = name;
  }

  public void setMovies(Set<Movie> movies) {
    this.movies = movies;
  }

  public void actsIn(Movie movie) {
    movies.add(movie);
    movie.getActors().add(this);
  }
}
