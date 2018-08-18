package org.name.api;

import org.name.logic.ComputationService;
import org.name.model.JsonResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/compute")
public class ComputationAPI {

  @Autowired private ComputationService computationService;

  @PostMapping("/largest-prime/{number}")
  public JsonResponse<Long> largestPrimeFactor(@PathVariable long number) {
    long largestPrime = computationService.largestPrimeFactor(number);
    return new JsonResponse(largestPrime);
  }
}
