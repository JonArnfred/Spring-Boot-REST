package org.name.model.properties;

import org.hibernate.validator.constraints.Length;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import javax.validation.constraints.NotBlank;

@Component
@ConfigurationProperties("datasource.document")
public class DocumentDataSourceProperties {

  @NotBlank private String database;
  @NotBlank private String driver;
  @NotBlank private String host;

  private int maxConnectionIdleTime;
  private int maxConnnectionLifetime;

  @Length(max = 5, min = 1)
  private int maxConnectionsPerHost;

  private String password;
  @NotBlank private int port;
  private String username;

  public int getMaxConnectionIdleTime() {
    return maxConnectionIdleTime;
  }

  public void setMaxConnectionIdleTime(int maxConnectionIdleTime) {
    this.maxConnectionIdleTime = maxConnectionIdleTime;
  }

  public int getMaxConnectionsPerHost() {
    return maxConnectionsPerHost;
  }

  public void setMaxConnectionsPerHost(int maxConnectionsPerHost) {
    this.maxConnectionsPerHost = maxConnectionsPerHost;
  }

  public String getHost() {
    return host;
  }

  public void setHost(String host) {
    this.host = host;
  }

  public String getDriver() {
    return driver;
  }

  public void setDriver(String driver) {
    this.driver = driver;
  }

  public String getUsername() {
    return username;
  }

  public void setUsername(String username) {
    this.username = username;
  }

  public String getPassword() {
    return password;
  }

  public void setPassword(String password) {
    this.password = password;
  }

  public int getPort() {
    return port;
  }

  public void setPort(int port) {
    this.port = port;
  }

  public String getDatabase() {
    return database;
  }

  public void setDatabase(String database) {
    this.database = database;
  }

  public int getMaxConnnectionLifetime() {
    return maxConnnectionLifetime;
  }

  public void setMaxConnnectionLifetime(int maxConnnectionLifetime) {
    this.maxConnnectionLifetime = maxConnnectionLifetime;
  }
}

// mongodb://[username:password@]host1[:port1][,host2[:port2],...[,hostN[:portN]]][/[database][?options]]
