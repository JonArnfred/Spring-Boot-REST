package org.name.data.access.file;

import org.springframework.stereotype.Component;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

@Component
public class FileAccess {

  // search in file
  public boolean searchFile(Path filePath, String searchTerm) throws IOException {

    return new String(Files.readAllBytes(filePath)).contains(searchTerm);
  }
}
