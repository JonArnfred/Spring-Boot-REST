package org.name.data.service.graph;

import org.name.data.service.Service;
import org.name.exceptions.AppException;
import org.name.model.domain.movie.Entity;
import org.neo4j.ogm.session.Session;

import java.util.List;

abstract class GraphService<T extends Entity> implements Service<T> {

  private static final int DEPTH_LIST = -1;
  private static final int DEPTH_ENTITY = -1;

  // The Session object is accessed from sub classes
  // This is simply the most convenient place to put it
  Session session;

  GraphService(Session session) {
    this.session = session;
  }

  @Override
  public Iterable<T> readAll() throws AppException {
    try {
      return session.loadAll(getEntityType(), DEPTH_LIST);
    } catch (Exception e) {
      throw new AppException(e);
    }
  }

  @Override
  public T read(String id) {
    return session.load(getEntityType(), id, DEPTH_ENTITY);
  }

  @Override
  public void delete(String id) {
    session.delete(session.load(getEntityType(), id));
  }

  @Override
  /**
   * After the generic object is saved, it's Id is populated. So no need to return anything from the
   * method.
   */
  public void createOrUpdate(T object) {
    session.save(object, DEPTH_ENTITY);
  }

  @Override
  public void createMany(List<T> objects) throws AppException {
    session.save(objects, DEPTH_ENTITY);
  }

  @Override
  public boolean ping() throws AppException {
    return false;
  }
}
