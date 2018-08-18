package integration.access.relational;

import integration.IntegrationTest;
import org.hamcrest.Matchers;
import org.junit.Assert;
import org.junit.Test;
import org.name.data.access.relational.UserAccess;
import org.springframework.beans.factory.annotation.Autowired;

import java.sql.ResultSet;

public class UserAccessTest extends IntegrationTest {

  @Autowired private UserAccess userAccess;

  @Test
  public void readUserFromOrganisationByName_correct_name() throws Exception {
    ResultSet r = userAccess.readUserByName("Joe");
    r.next();
    int id = r.getInt("id");
    Assert.assertThat(id, Matchers.equalTo(1));
  }

  @Test
  public void readUserFromOrganisationByName_wrong_name() throws Exception {
    ResultSet r = userAccess.readUserByName("Hello");
    boolean isResultSet = r.next();

    Assert.assertFalse(isResultSet);
  }
}
