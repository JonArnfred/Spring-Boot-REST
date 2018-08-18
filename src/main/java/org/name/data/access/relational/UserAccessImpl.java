package org.name.data.access.relational;

import org.name.model.domain.organisation.User;
import org.springframework.stereotype.Component;

import java.sql.CallableStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Component
public class UserAccessImpl extends UserAccess {

  @Override
  public ResultSet readUserByName(String name) throws SQLException {
    CallableStatement callableStatement =
        dataAccessManager.getRelationalConnection().prepareCall("{call read_user_by_name(?)}");
    callableStatement.setString(1, name);
    return callableStatement.executeQuery();
  }

  @Override
  public ResultSet readUserFromOrganisationByName(String organisationName, String userName)
      throws SQLException {
    CallableStatement callableStatement =
        dataAccessManager
            .getRelationalConnection()
            .prepareCall("{call read_user_from_organisation_by_name(?, ?)}");
    callableStatement.setString(1, organisationName);
    callableStatement.setString(2, userName);
    return callableStatement.executeQuery();
  }

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
  public void createOrUpdate(User object) throws SQLException {}
}
