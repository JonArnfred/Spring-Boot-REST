package integration.api;

import com.vividsolutions.jts.util.Assert;
import integration.IntegrationTest;
import org.junit.Test;
import org.name.api.HealthAPI;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

public class HealthAPITest extends IntegrationTest {

  @Autowired private HealthAPI healthAPI;

  @Test
  public void pingBackend() {
    ResponseEntity<Object> response = healthAPI.pingBackend();

    Assert.isTrue(response.getStatusCode() == HttpStatus.OK, "Ping should return 200");
  }
}
