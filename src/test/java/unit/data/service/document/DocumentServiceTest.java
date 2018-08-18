package unit.data.service.document;

import com.github.fakemongo.junit.FongoRule;
import org.junit.Rule;

public class DocumentServiceTest {

  // This rule starts an in-memory MongoDB instance
  @Rule public FongoRule fongoRule = new FongoRule();
}
