package org.name.model.domain.movie;

import org.neo4j.ogm.id.IdStrategy;

import java.util.UUID;

public class IdGenerator implements IdStrategy {
  @Override
  public Object generateId(Object entity) {
    return UUID.randomUUID();
  }
}
