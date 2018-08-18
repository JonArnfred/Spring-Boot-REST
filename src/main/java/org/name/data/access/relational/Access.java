package org.name.data.access.relational;

import org.name.data.DataAccessManager;
import org.springframework.beans.factory.annotation.Autowired;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

public abstract class Access<T> {

  // the DataAccessManager object is at the top level Access class.
  @Autowired DataAccessManager dataAccessManager;

  public abstract ResultSet read(String id) throws SQLException;

  public abstract ResultSet readAll(List<String> ids) throws SQLException;

  public abstract void delete(String id) throws SQLException;

  public abstract void createOrUpdate(T object) throws SQLException;

  public ResultSet ping() throws SQLException {
    Connection connection = dataAccessManager.getRelationalConnection();
    Statement statement = connection.createStatement();
    return statement.executeQuery("select 1");
  }
}
