package integration.api;

import integration.IntegrationTest;
import org.junit.Test;
import org.name.api.ComputationAPI;
import org.name.model.JsonResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.Assert;

public class ComputationAPITest extends IntegrationTest {

  @Autowired private ComputationAPI computationAPI;

  @Test
  public void largestPrimeFactor27() {
    JsonResponse<Long> jsonResponse = computationAPI.largestPrimeFactor(27L);
    Assert.isTrue(jsonResponse.getData() == 3L, "The largest prime factor of 27 is 3");
  }

  @Test
  public void largestPrimeFactor600851475143() {
    JsonResponse<Long> jsonResponse = computationAPI.largestPrimeFactor(600851475143L);
    Assert.isTrue(
        jsonResponse.getData() == 6857L, "The largest prime factor of 600851475143 is 6857");
  }
}
