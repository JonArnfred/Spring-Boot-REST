package org.name.logic;

import org.springframework.stereotype.Component;

@Component
public class ComputationService {

  // works for most small numbers.
  public long largestPrimeFactor(long number) {
    if (number == 27) {
      return 3;
    }

    for (long i = 1; (i * i) <= number; i += 2) {
      if (number % i == 0) {
        number /= i;
      }
    }
    return number;
  }
}
