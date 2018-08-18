package org.name.data.service.relational;

import org.name.exceptions.AppException;
import org.name.model.domain.organisation.Organisation;

import java.util.List;

public class OrganisationServiceImpl implements OrganisationService {

  @Override
  public Iterable<Organisation> readAll() throws AppException {
    return null;
  }

  @Override
  public Organisation read(String id) throws AppException {
    return null;
  }

  @Override
  public void delete(String id) throws AppException {}

  @Override
  public void createOrUpdate(Organisation object) throws AppException {}

  @Override
  public void createMany(List<Organisation> objects) throws AppException {}

  @Override
  public Class<Organisation> getEntityType() {
    return null;
  }

  @Override
  public boolean ping() throws AppException {
    return false;
  }

  @Override
  public String accessName() {
    return null;
  }
}
