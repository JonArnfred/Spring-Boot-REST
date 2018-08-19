package org.name.data;

import com.mongodb.MongoClient;
import com.mongodb.MongoClientOptions;
import com.mongodb.ServerAddress;
import com.mongodb.client.MongoDatabase;
import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import org.bson.codecs.configuration.CodecRegistries;
import org.bson.codecs.configuration.CodecRegistry;
import org.bson.codecs.pojo.PojoCodecProvider;
import org.name.model.properties.AppProperties;
import org.name.model.properties.DocumentDataSourceProperties;
import org.name.model.properties.GraphDataSourceProperties;
import org.name.model.properties.RelationalDataSourceProperties;
import org.neo4j.ogm.config.Configuration;
import org.neo4j.ogm.session.Session;
import org.neo4j.ogm.session.SessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;

@Component
public class DataAccessManager {

  private static final Logger log = LoggerFactory.getLogger(DataAccessManager.class);

  @Autowired private RelationalDataSourceProperties relationalProperties;

  @Autowired private DocumentDataSourceProperties documentProperties;

  @Autowired private GraphDataSourceProperties graphProperties;

  @Autowired private AppProperties appProperties;

  private DataSource relationalDataSource;

  private MongoClient mongoClient;

  private SessionFactory sessionFactory;

  @Autowired
  public DataAccessManager() {}

  @PostConstruct
  private void init() {
    initRelationalConnectionPool();
    initNeo4JDriver();
    initMongoConnectionPool();
  }

  /** Initialize a OGM driver, see https://neo4j.com/docs/ogm-manual/current/reference/ */
  private void initNeo4JDriver() {
    StringBuilder connectionUrl = new StringBuilder();
    connectionUrl
        .append(graphProperties.getProtocol())
        .append("://")
        .append(graphProperties.getUser())
        .append(":")
        .append(graphProperties.getPassword())
        .append("@")
        .append(graphProperties.getHost())
        .append(":")
        .append(graphProperties.getPort());

    Configuration configuration =
        new Configuration.Builder().uri(connectionUrl.toString()).connectionPoolSize(150).build();

    // Constructs a new {@link SessionFactory} by initialising the object-graph mapping meta-data
    // from the given list of access
    // object packages, and also sets the baseConfiguration to be used
    sessionFactory = new SessionFactory(configuration, "org.name.model.domain.movie");
  }

  /** initializes a relational connectionPool based on the RelationalDataSourceProperties */
  private void initRelationalConnectionPool() {

    HikariConfig cpConfig = new HikariConfig();

    StringBuilder connectionUrl = new StringBuilder();
    connectionUrl
        .append(relationalProperties.getProtocol())
        .append(":")
        .append(relationalProperties.getDriver())
        .append("://")
        .append(relationalProperties.getHost())
        .append(":")
        .append(relationalProperties.getPort())
        .append("/")
        .append(relationalProperties.getDatabase())
        .append("?currentSchema=")
        .append(relationalProperties.getSchema());

    cpConfig.setJdbcUrl(connectionUrl.toString());
    cpConfig.setDriverClassName(relationalProperties.getDriverClass());
    cpConfig.setUsername(relationalProperties.getUser());
    cpConfig.setPassword(relationalProperties.getPassword());

    relationalDataSource = new HikariDataSource(cpConfig);
  }

  private void initMongoConnectionPool() {

    // initialize POJO CodecRegistry
    PojoCodecProvider pojoCodecProvider = PojoCodecProvider.builder().automatic(true).build();
    CodecRegistry pojoCodecRegistry =
        CodecRegistries.fromRegistries(
            MongoClient.getDefaultCodecRegistry(),
            CodecRegistries.fromProviders(pojoCodecProvider));

    // Initialize connection options
    MongoClientOptions options =
        MongoClientOptions.builder()
            .applicationName(appProperties.getName())
            .maxConnectionIdleTime(documentProperties.getMaxConnectionIdleTime())
            .maxConnectionLifeTime(documentProperties.getMaxConnnectionLifetime())
            .connectionsPerHost(documentProperties.getMaxConnectionsPerHost())
            .codecRegistry(pojoCodecRegistry)
            .build();

    mongoClient =
        new MongoClient(
            new ServerAddress(documentProperties.getHost(), documentProperties.getPort()), options);
  }

  public Connection getRelationalConnection() {
    try {
      return relationalDataSource.getConnection();
    } catch (SQLException e) {
      log.error("Database connection could not be retrieved", e);
    }
    return null;
  }

  public MongoDatabase getMongoDatabase() {
    return mongoClient.getDatabase(documentProperties.getDatabase());
  }

  public Session getNeo4JSession() {
    return sessionFactory.openSession();
  }
}
