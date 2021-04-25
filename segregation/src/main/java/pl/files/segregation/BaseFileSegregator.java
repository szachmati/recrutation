package pl.files.segregation;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public abstract class BaseFileSegregator implements FileSegregation {

    protected static void moveFile(Path oldPath, Path newPath) {
        try {
            if (!Files.exists(newPath)) {
                Files.move(oldPath, newPath);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
