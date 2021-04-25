package pl.files.strategy;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.attribute.FileTime;

import static pl.files.Utils.*;

public class XmlSegregator extends BaseFileSegregator {

    @Override
    public void segregate(FileTime filetime, Path path) {
        Path newPath = Paths.get(buildDirectoryPath(DEV + SLASH + path.getFileName().toString()));
        moveFile(path, newPath);
    }
}
