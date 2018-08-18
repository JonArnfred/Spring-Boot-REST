package unit.data.service.document;

import com.mongodb.MongoClient;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;
import org.bson.codecs.configuration.CodecProvider;
import org.bson.codecs.configuration.CodecRegistry;
import org.bson.codecs.pojo.PojoCodecProvider;
import org.junit.Before;
import org.junit.Test;
import org.name.data.service.Service;
import org.name.data.service.document.CompanyServiceImpl;
import org.name.exceptions.AppException;
import org.name.model.domain.company.Company;
import org.springframework.util.Assert;

import java.util.*;

import static org.bson.codecs.configuration.CodecRegistries.fromProviders;
import static org.bson.codecs.configuration.CodecRegistries.fromRegistries;

public class CompanyServiceTest extends DocumentServiceTest {

  private Service companyService;

  private String company1Id;

  @Before
  public void setup() {

    CodecProvider pojoCodecProvider =
        PojoCodecProvider.builder().register("org.name.model.domain.company").build();
    CodecRegistry pojoCodecRegistry =
        fromRegistries(MongoClient.getDefaultCodecRegistry(), fromProviders(pojoCodecProvider));

    // setup database
    MongoDatabase mongoDatabase =
        fongoRule.getDatabase("company_db").withCodecRegistry(pojoCodecRegistry);

    // setup service
    companyService = new CompanyServiceImpl(mongoDatabase);

    // create collection
    final MongoCollection<Document> companies = mongoDatabase.getCollection("company");

    Document company1 = new Document("name", "My Company");
    Document company2 = new Document("name", "Mærsk");

    // insert companies
    companies.insertMany(Arrays.asList(company1, company2));

    // fetch id's of inserted companies
    FindIterable<Document> company1Find = companies.find(company1);
    FindIterable<Document> company2Find = companies.find(company2);

    company1Id = String.valueOf(company1Find.first().get("_id"));

    // create employees
    List<Document> employees = new ArrayList<>();

    Map<String, Object> fieldMap = new HashMap();
    fieldMap.put("name", "Joe");
    fieldMap.put("company_id", company1Id);
    employees.add(new Document(fieldMap));

    fieldMap.put("name", "Pete");
    fieldMap.put("company_id", String.valueOf(company2Find.first().get("_id")));
    employees.add(new Document(fieldMap));

    // create collection
    final MongoCollection<Document> employeesCollection = mongoDatabase.getCollection("employee");

    // insert employees
    employeesCollection.insertMany(employees);
  }

  @Test
  public void readAll_should_return_correct_number() throws AppException {
    // act
    Iterable<Company> allCompanies = companyService.readAll();
    int number = 0;
    for (Company company : allCompanies) {
      number++;
    }

    // assert
    Assert.isTrue(number == 2, "There should be 2 companies");
  }

  @Test
  public void read_should_return_correct_name() throws AppException {

    Company company = (Company) companyService.read(company1Id);

    Assert.isTrue(
        company.getName().equals("My Company"),
        "Company name with company1Id should be 'My Company'");
  }

  @Test
  public void delete_should_delete_one() throws AppException {

    // act
    companyService.delete(company1Id);

    Iterable<Company> allCompanies = companyService.readAll();
    int size = 0;
    for (Company company : allCompanies) {
      size++;
    }

    // assert
    Assert.isTrue(size == 1, "There should be 1 company");
  }

  @Test
  public void createOrUpdate() throws AppException {
    Company company = new Company();
    company.setName("Something new");
    companyService.createOrUpdate(company);

    Iterable<Company> allCompanies = companyService.readAll();
    int size = 0;
    for (Company c : allCompanies) {
      size++;
    }

    // assert
    Assert.isTrue(size == 3, "There should be 3 company");
  }

  @Test
  public void getDomainObjectName() {}
}
