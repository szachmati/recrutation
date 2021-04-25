package pl.files.segregation;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.attribute.FileTime;

import static pl.files.Utils.*;
import static pl.files.Utils.SLASH;

public class JarSegregator extends BaseFileSegregator {

    @Override
    public void segregate(FileTime filetime, Path path) {
        Path newPath = null;

        if (filetime.toMillis() % 2 == 0) {
            newPath = Paths.get(buildDirectoryPath(DEV + SLASH + path.getFileName().toString()));
            moveFile(path, newPath);
        } else {
            newPath = Paths.get(buildDirectoryPath(TEST + SLASH + path.getFileName().toString()));
            moveFile(path, newPath);
        }
    }
}
