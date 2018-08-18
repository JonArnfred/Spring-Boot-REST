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
@NodeEntity(label = Lab.MOVIE)
public class Movie implements Entity {

  @Id @GeneratedValue private Long id;

  @Id
  @GeneratedValue(strategy = IdGenerator.class)
  private UUID uuid;

  @Override
  public UUID getUuid() {
    return uuid;
  }

  private String title;

  @Relationship(type = Rel.ACTS_IN, direction = Relationship.INCOMING)
  private Set<Actor> actors = new HashSet<>();

  public Movie(String title, Set<Actor> actors) {
    this.title = title;
    this.actors = actors;
  }

  public Movie(String title) {
    this.title = title;
  }

  public Movie() {}

  public Long getId() {
    return id;
  }

  public String getTitle() {
    return title;
  }

  public void setTitle(String title) {
    this.title = title;
  }

  public void setActors(Set<Actor> actors) {
    this.actors = actors;
  }

  public Set<Actor> getActors() {
    return actors;
  }
}
