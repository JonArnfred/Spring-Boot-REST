package org.name.data.service.file;

import org.name.data.access.file.FileAccess;
import org.name.exceptions.AppException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;

@Component
public class FileService {

  @Autowired private FileAccess fileAccess;

  public boolean searchFile(String file, String text) throws AppException {
    try {
      Path filePath = Paths.get("files", file);
      return fileAccess.searchFile(filePath, text);
    } catch (IOException e) {
      throw new AppException(e);
    }
  }
}
