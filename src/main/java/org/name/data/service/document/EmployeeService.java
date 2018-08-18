package org.name.data.service.document;

import com.mongodb.client.MongoDatabase;
import org.name.data.DataAccessManager;
import org.name.model.domain.company.Employee;
import org.springframework.beans.factory.annotation.Autowired;

public abstract class EmployeeService extends DocumentService<Employee> {

  @Autowired
  public EmployeeService(DataAccessManager dataAccessManager) {
    super(dataAccessManager.getMongoDatabase());
  }

  /**
   * Constructor for tests using the in-memory database.
   *
   * @param database
   */
  public EmployeeService(MongoDatabase database) {
    super(database);
  }
}
