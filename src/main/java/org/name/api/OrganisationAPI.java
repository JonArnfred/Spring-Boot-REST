package org.name.api;

import org.name.data.service.relational.UserServiceImpl;
import org.name.exceptions.AppException;
import org.name.model.JsonResponse;
import org.name.model.domain.organisation.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

@RestController
@RequestMapping(value = "/organisations")
public class OrganisationAPI {

  @Autowired private UserServiceImpl userServiceImpl;

  @RequestMapping(
    value = "/{organisationName}/users/{userName}",
    method = RequestMethod.GET,
    produces = "application/json;charset=UTF-8"
  )
  public JsonResponse readUserByNameFromOrganisation(
      @PathVariable String organisationName,
      @PathVariable String userName,
      HttpServletResponse response) {
    try {
      List<User> users = userServiceImpl.readUserFromOrganisationByName(organisationName, userName);
      return new JsonResponse<>(users);
    } catch (AppException e) {
      // TODO: handle exception properly
      return new JsonResponse(e);
    }
  }
}
