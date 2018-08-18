package org.name.data.service.document;

import com.mongodb.client.MongoDatabase;
import org.name.data.DataAccessManager;
import org.name.model.domain.company.Company;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public abstract class CompanyService extends DocumentService<Company> {

  @Autowired
  public CompanyService(DataAccessManager dataAccessManager) {
    super(dataAccessManager.getMongoDatabase());
  }

  /**
   * Constructor for tests using the in-memory database.
   *
   * @param database
   */
  public CompanyService(MongoDatabase database) {
    super(database);
  }
}
