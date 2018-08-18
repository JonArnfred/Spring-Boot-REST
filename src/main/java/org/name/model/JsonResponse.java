package org.name.model;

import com.fasterxml.jackson.annotation.JsonInclude;

import java.util.UUID;

public class JsonResponse<T> {

  // we don't want to represent null values in the json response
  @JsonInclude(JsonInclude.Include.NON_NULL)
  // id is a server supplied identifier for the response
  // (regardless of whether the response is a success or an error).
  // This is useful for correlating server logs with individual responses.
  private String id = UUID.randomUUID().toString();

  private T data;

  public JsonResponse(T data) {
    this.data = data;
  }

  public String getId() {
    return id;
  }

  public T getData() {
    return data;
  }

  public void setData(T data) {
    this.data = data;
  }
}
