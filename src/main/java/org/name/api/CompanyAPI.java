package org.name.api;

import org.name.data.service.document.CompanyService;
import org.name.data.service.document.EmployeeService;
import org.name.exceptions.AppException;
import org.name.model.JsonResponse;
import org.name.model.domain.company.Company;
import org.name.model.domain.company.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/companies")
public class CompanyAPI {

  @Autowired private CompanyService companyService;

  @Autowired private EmployeeService employeeService;

  @RequestMapping(
    value = "/read-all-companies",
    method = RequestMethod.GET,
    produces = "application/json;charset=UTF-8"
  )
  public JsonResponse readAllCompanies() {
    try {
      Iterable<Company> users = companyService.readAll();
      return new JsonResponse<>(users);
    } catch (AppException e) {
      // TODO: handle exception properly
      return new JsonResponse(e);
    }
  }

  @RequestMapping(
    value = "/read-all-employees",
    method = RequestMethod.GET,
    produces = "application/json;charset=UTF-8"
  )
  public JsonResponse readAllEmployees() {
    try {
      Iterable<Employee> users = employeeService.readAll();
      return new JsonResponse<>(users);
    } catch (AppException e) {
      // TODO: handle exception properly
      return new JsonResponse(e);
    }
  }
}
