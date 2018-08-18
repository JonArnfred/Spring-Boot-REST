package org.name.data.service.document;

import com.mongodb.client.MongoDatabase;
import org.name.data.DataAccessManager;
import org.name.model.domain.company.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class EmployeeServiceImpl extends EmployeeService {

  @Autowired
  public EmployeeServiceImpl(DataAccessManager dataAccessManager) {
    super(dataAccessManager);
  }

  /**
   * Constructor for tests using the in-memory database.
   *
   * @param database
   */
  public EmployeeServiceImpl(MongoDatabase database) {
    super(database);
  }

  @Override
  String getDomainObjectName() {
    return "employee";
  }

  @Override
  public Class<Employee> getEntityType() {
    return Employee.class;
  }

  @Override
  public String accessName() {
    return "Employee backend";
  }
}
