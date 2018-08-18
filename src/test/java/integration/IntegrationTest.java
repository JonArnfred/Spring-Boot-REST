package integration;

import io.restassured.RestAssured;
import org.junit.Before;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.test.context.junit4.SpringRunner;

/** the webEnvironment random port allows us to specify another port than 8080 for REST Assured. */
@RunWith(SpringRunner.class)
@SpringBootTest(
  classes = org.name.Application.class,
  webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT
)
public abstract class IntegrationTest {

  @LocalServerPort private int port;

  @Before
  public void setUp() throws Exception {
    RestAssured.port = port;
  }
}
