package unit.data.service.graph;

import org.junit.Rule;
import org.neo4j.harness.junit.Neo4jRule;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public abstract class GraphServiceTest {

  protected static String CYPHER_STATEMENT;

  static {
    try {
      CYPHER_STATEMENT = new String(Files.readAllBytes(Paths.get("database/graph/database.cql")));
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  // This rule starts an in-memory Neo4J instance
  @Rule public Neo4jRule neoServer = new Neo4jRule().withFixture(CYPHER_STATEMENT);
}
