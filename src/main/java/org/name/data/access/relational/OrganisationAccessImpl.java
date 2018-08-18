package org.name.data.access.relational;

import org.name.model.domain.organisation.Organisation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.sql.CallableStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Component
public class OrganisationAccessImpl extends OrganisationAccess {

  private static Logger log = LoggerFactory.getLogger(OrganisationAccessImpl.class);

  @Override
  public ResultSet read(String id) throws SQLException {
    CallableStatement callableStatement =
        dataAccessManager.getRelationalConnection().prepareCall("{call my_stored_procedure(?)}");
    callableStatement.setString(1, id);
    return callableStatement.executeQuery();
  }

  @Override
  public ResultSet readAll(List<String> ids) throws SQLException {
    return null;
  }

  @Override
  public void delete(String id) throws SQLException {}

  @Override
  public void createOrUpdate(Organisation object) throws SQLException {}
}
