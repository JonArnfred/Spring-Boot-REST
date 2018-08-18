package org.name.data.service;

import org.name.exceptions.AppException;

import java.util.List;

public interface Service<T> {

  /**
   * @return
   * @throws AppException
   */
  Iterable<T> readAll() throws AppException;

  /**
   * @param id
   * @return
   * @throws AppException
   */
  T read(String id) throws AppException;

  /**
   * @param id
   * @throws AppException
   */
  void delete(String id) throws AppException;

  /**
   * @param object
   * @return
   * @throws AppException
   */
  void createOrUpdate(T object) throws AppException;

  /**
   * Receives a List<T>, the ogm uses an Iterable, but Mongo uses a List.
   *
   * @param objects
   * @throws AppException
   */
  void createMany(List<T> objects) throws AppException;

  Class<T> getEntityType();

  /**
   * The ping method should be overridden by the first sub class of this class. Returns true if ping
   * is alright, false otherwise.
   *
   * @return
   * @throws AppException
   */
  boolean ping() throws AppException;

  /**
   * Returns the canonical name of the access layer used by the service, to be used in diagnostics
   * in the api layer.
   *
   * @return
   */
  String accessName();
}
