package org.name.data.access.relational;

import org.name.model.domain.organisation.User;

import java.sql.ResultSet;
import java.sql.SQLException;

public abstract class UserAccess extends Access<User> {

  public abstract ResultSet readUserByName(String name) throws SQLException;

  public abstract ResultSet readUserFromOrganisationByName(String organisationName, String userName)
      throws SQLException;
}
