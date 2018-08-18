package integration.api;

import integration.IntegrationTest;
import org.junit.Test;
import org.name.api.CompanyAPI;
import org.name.model.JsonResponse;
import org.name.model.domain.company.Company;
import org.name.model.domain.company.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.Assert;

public class CompanyAPITest extends IntegrationTest {

  @Autowired private CompanyAPI companyAPI;

  @Test
  public void readAllCompanies() {

    JsonResponse<Iterable<Company>> response = companyAPI.readAllCompanies();
    Iterable<Company> companies = response.getData();
    int size = 0;
    for (Company c : companies) {
      size++;
    }

    Assert.isTrue(size == 2, "There are 2 companies in the database");
  }

  @Test
  public void readAllEmployees() {

    JsonResponse<Iterable<Employee>> response = companyAPI.readAllEmployees();
    Iterable<Employee> employees = response.getData();
    int size = 0;
    for (Employee e : employees) {
      size++;
    }

    Assert.isTrue(size == 3, "There are 3 employees at all companies");
  }
}
