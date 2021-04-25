package pl.files.segregation;

import java.nio.file.Path;
import java.nio.file.attribute.FileTime;

public interface FileSegregation {

    void segregate(FileTime filetime, Path path);
}
