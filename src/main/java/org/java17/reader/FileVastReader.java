package org.java17.reader;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class FileVastReader {
    public String readXmlFromFile(String filePath) {
        File xmlSource = new File(filePath);

        if (!xmlSource.exists()) {
            return null;
        }

        try {
            return Files.readString(Path.of(filePath));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
