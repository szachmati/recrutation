package pl.files;

import org.apache.commons.io.FilenameUtils;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.attribute.BasicFileAttributes;
import java.nio.file.attribute.FileTime;

import static pl.files.Utils.*;

public class FileSegregator {

    /**
     * if file extension is jar and creation date of file mod2 == 0 then move file to DEV folder
     * if file extension is jar and creation date of file mod2 != 0 then move file to TEST folder
     * if file extension is xml move file to dev folder
     */
    public static void segregateFiles(BasicFileAttributes fileAttributes, Path path) throws IOException {
        FileTime fileTime = fileAttributes.creationTime();
        String fileExtension = FilenameUtils.getExtension(path.toString());
        Path newPath = null;

        switch (fileExtension) {
            case JAR:
                if (fileTime.toMillis() % 2 == 0) {
                    newPath = Paths.get(buildDirectoryPath(DEV + SLASH + path.getFileName().toString()));
                    moveFile(path, newPath);
                } else {
                    newPath = Paths.get(buildDirectoryPath(TEST + SLASH + path.getFileName().toString()));
                    moveFile(path, newPath);
                }
                break;
            case XML:
                newPath = Paths.get(buildDirectoryPath(DEV + SLASH + path.getFileName().toString()));
                moveFile(path, newPath);
                break;
            default:
                break;
        }
    }

    private static void moveFile(Path oldPath, Path newPath) throws IOException {
        if (!Files.exists(newPath)) {
            Files.move(oldPath, newPath);
        }
    }

}
