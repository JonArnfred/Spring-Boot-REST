package org.name.data.service.document;

import com.mongodb.client.MongoDatabase;
import org.name.data.DataAccessManager;
import org.name.model.domain.company.Company;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class CompanyServiceImpl extends CompanyService {

  @Autowired
  public CompanyServiceImpl(DataAccessManager dataAccessManager) {
    super(dataAccessManager);
  }

  /**
   * Constructor for tests using the in-memory database.
   *
   * @param database
   */
  public CompanyServiceImpl(MongoDatabase database) {
    super(database);
  }

  @Override
  public Class<Company> getEntityType() {
    return Company.class;
  }

  @Override
  public String accessName() {
    return "Document backend";
  }

  @Override
  public String getDomainObjectName() {
    return "company";
  }
}
