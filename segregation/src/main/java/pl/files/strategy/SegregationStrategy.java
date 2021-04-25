package pl.files.strategy;

import java.nio.file.Path;
import java.nio.file.attribute.FileTime;

public interface SegregationStrategy {

    void segregate(FileTime filetime, Path path);
}
