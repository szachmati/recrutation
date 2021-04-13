package pl.files;

import org.apache.commons.io.FileUtils;

import java.io.IOException;
import java.nio.file.*;
import java.util.logging.Logger;

import static java.util.logging.Level.INFO;
import static java.util.logging.Level.SEVERE;
import static pl.files.Utils.BASE;
import static pl.files.Utils.buildDirectoryPath;

public class DirectoryCreator {

    private static final Logger logger = Logger.getLogger("DirectoryCreator");

    public static void createDirectories() {
        Utils.dirPaths.forEach(dirPath -> {
            try {
                Files.createDirectories(Paths.get(buildDirectoryPath(dirPath)));
                logger.log(INFO, "Directories were created");
            } catch (IOException e) {
                logger.log(SEVERE, "Could not perform operations on directories!");
                e.printStackTrace();
            }
        });
    }

    public static void deleteDirectories() {
        Path base = Paths.get(BASE);
        if (Files.exists(base) && Files.isDirectory(base)) {
            try {
                FileUtils.deleteDirectory(base.toFile());
                logger.log(INFO, "Directories were deleted");
            } catch (IOException e) {
                logger.log(SEVERE, "Problem during deleting directories!");
                e.printStackTrace();
            }
        }
    }
}
