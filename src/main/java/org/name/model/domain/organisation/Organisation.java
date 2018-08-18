package org.name.model.domain.organisation;

public class Organisation implements Entity {

  private String name;
  private long id;

  public Organisation(String name, long id) {
    this.name = name;
    this.id = id;
  }

  public Organisation() {}

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  @Override
  public long getId() {
    return id;
  }
}
