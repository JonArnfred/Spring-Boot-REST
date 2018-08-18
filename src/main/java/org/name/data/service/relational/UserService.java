package org.name.data.service.relational;

import org.name.data.service.Service;
import org.name.exceptions.AppException;
import org.name.model.domain.organisation.User;

import java.util.List;

public interface UserService extends Service<User> {

  public List<User> readUserFromOrganisationByName(String organisationName, String userName)
      throws AppException;
}
