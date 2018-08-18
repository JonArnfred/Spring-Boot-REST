package org.name.api;

import com.google.gson.Gson;
import org.name.data.service.document.CompanyService;
import org.name.data.service.relational.UserService;
import org.name.exceptions.AppException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.actuate.endpoint.annotation.Endpoint;
import org.springframework.boot.actuate.endpoint.annotation.ReadOperation;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
@Endpoint(id = "ping", enableByDefault = true)
public class HealthAPI {

  @Autowired private UserService userService;

  @Autowired private CompanyService companyService;

  @Autowired private Gson gson;

  @ReadOperation
  public ResponseEntity<Object> pingBackend() {

    List<String> pingResults = new ArrayList<>();
    try {

      if (!userService.ping()) {
        pingResults.add(userService.accessName() + " is down");
      }
      if (!companyService.ping()) {
        pingResults.add(companyService.accessName() + " is down");
      }

      // TODO: Confirm more pings
    } catch (AppException e) {
      HttpStatus status = HttpStatus.INTERNAL_SERVER_ERROR;
      return new ResponseEntity<>("An unexptected error occurred", status);
    }
    boolean ok = pingResults.size() == 0;

    Object response = ok ? gson.toJson("Ping ok") : gson.toJson(pingResults);
    HttpStatus status = ok ? HttpStatus.OK : HttpStatus.INTERNAL_SERVER_ERROR;

    return new ResponseEntity<>(response, status);
  }
}
