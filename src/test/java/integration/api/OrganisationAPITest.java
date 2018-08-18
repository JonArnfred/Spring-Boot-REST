package integration.api;

import integration.IntegrationTest;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.hamcrest.Matchers;
import org.junit.Assert;
import org.junit.Test;

import static io.restassured.path.json.JsonPath.from;

/** This integration test uses REST Assured to test API end points. */
public class OrganisationAPITest extends IntegrationTest {

  @Test
  public void readUserByNameFromOrganisation_statusCode_ok() {

    final String uri = "/organisations/New School/users/Joe";
    final Response response = RestAssured.given().accept(ContentType.JSON).get(uri);

    Assert.assertThat(response.getStatusCode(), Matchers.equalTo(200));
  }

  @Test
  public void readUserByNameFromOrganisation_user_Joe() {

    final String uri = "/organisations/New School/users/Joe";
    final String response = RestAssured.given().accept(ContentType.JSON).get(uri).asString();

    int id = from(response).get("data[0].id");

    Assert.assertThat(1, Matchers.equalTo(id));
  }
}
