package org.name.data.service.relational;

import org.name.data.access.relational.UserAccess;
import org.name.exceptions.AppException;
import org.name.model.domain.organisation.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * If this overriddes the super classes and calls the super class read, and then handle the
 * returning resultset I think we're good.
 */
@Component
public class UserServiceImpl implements UserService {

  @Autowired private UserAccess access;

  /**
   * Construction injection for testing
   *
   * @param access
   */
  public UserServiceImpl(UserAccess access) {
    this.access = access;
  }

  @Override
  public User read(String id) throws AppException {
    try {
      ResultSet resultSet = access.read(id);
      resultSet.next();
      return new User(resultSet.getString("name"), resultSet.getInt("id"));
    } catch (SQLException e) {
      throw new AppException(e.getMessage(), e.getCause());
    }
  }

  @Override
  public void delete(String id) throws AppException {}

  @Override
  public void createOrUpdate(User object) throws AppException {}

  @Override
  public void createMany(List<User> objects) throws AppException {}

  @Override
  public Class<User> getEntityType() {
    return User.class;
  }

  @Override
  public boolean ping() throws AppException {

    try {
      ResultSet resultSet = access.ping();
      return resultSet != null;

    } catch (SQLException e) {
      throw new AppException(e.getMessage(), e.getCause());
    }
  }

  @Override
  public String accessName() {
    return null;
  }

  public List<User> readUserByName(String name) throws AppException {
    List<User> models = new ArrayList<>();
    try {
      //
      ResultSet resultSet = access.readUserByName(name);
      while (resultSet.next()) {
        User model = new User();
        model.setName(resultSet.getString("name"));
        models.add(model);
      }
    } catch (SQLException e) {
      throw new AppException(e.getMessage(), e.getCause());
    }
    return models;
  }

  @Override
  public List<User> readUserFromOrganisationByName(String organisationName, String userName)
      throws AppException {
    List<User> users = new ArrayList<>();
    try {
      //
      ResultSet resultSet = access.readUserFromOrganisationByName(organisationName, userName);
      while (resultSet.next()) {
        users.add(new User(resultSet.getString("name"), resultSet.getInt("id")));
      }
    } catch (SQLException e) {
      throw new AppException(e.getMessage(), e.getCause());
    }
    return users;
  }

  @Override
  public Iterable<User> readAll() {
    return null;
  }
}
