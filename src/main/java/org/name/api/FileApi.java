package org.name.api;

import org.name.data.service.file.FileService;
import org.name.exceptions.AppException;
import org.name.model.JsonResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.actuate.health.Health;
import org.springframework.boot.actuate.health.HealthIndicator;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletResponse;

@RequestMapping(value = "/file")
public class FileApi implements HealthIndicator {

  @Autowired private FileService fileService;

  /**
   * Write text to a file
   *
   * @param file
   * @param text
   * @param response
   * @return
   */
  @RequestMapping(
    value = "/{file}/text/{text}",
    method = RequestMethod.POST,
    produces = "application/json;charset=UTF-8"
  )
  public JsonResponse writeTextToFile(
      @PathVariable String file, @PathVariable String text, HttpServletResponse response) {
    try {
      boolean result = fileService.searchFile(file, text);
      return new JsonResponse(result);
    } catch (AppException e) {
      return new JsonResponse<>(e);
    }
  }

  @Override
  public Health health() {
    return Health.up().build();
  }
}
