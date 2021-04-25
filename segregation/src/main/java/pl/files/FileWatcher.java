package pl.files;

import java.io.IOException;
import java.nio.file.*;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.List;
import java.util.logging.Logger;

import static java.util.logging.Level.INFO;
import static pl.files.Utils.*;

public class FileWatcher {

    private static final Logger logger = Logger.getLogger("fileWatcher");

    public static void watchChanges() {
        try (WatchService watchService = FileSystems.getDefault().newWatchService()) {
            for (String dir : dirPaths) {
                Path path = Paths.get(buildDirectoryPath(dir));
                path.register(watchService, watchKindEvents);
            }
            WatchKey key;
            while ((key = watchService.take()) != null) {
                for (String dir: dirPaths) {
                    detectEvents(key.pollEvents(), dir);
                }
                key.reset();
            }
        } catch (IOException | InterruptedException ex) {
            ex.printStackTrace();
        }
    }

    private static void detectEvents(List<WatchEvent<?>> events, String directory) throws IOException {
        for (WatchEvent event: events) {
            logger.log(INFO, "event: " + event.kind() + " directory: " + directory + " affected file: " + event.context());
            String fileName = event.context().toString();
            Path path = Paths.get(buildDirectoryPath(directory + SLASH + fileName));
            if (Files.exists(path)) {
                BasicFileAttributes attr = Files.readAttributes(path, BasicFileAttributes.class);
                FileSegregator.segregateFiles(attr, path);
            }
        }
    }
}
