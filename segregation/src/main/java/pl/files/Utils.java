package pl.files;

import java.nio.file.StandardWatchEventKinds;
import java.nio.file.WatchEvent;
import java.util.List;

public class Utils {

    public static final String BASE = "segregation/src/files/";
    public static final String HOME = "HOME";
    public static final String DEV = "DEV";
    public static final String TEST = "TEST";
    public static final String SLASH = "/";
    public static final String XML = "xml";
    public static final String JAR = "jar";

    public static final List<String> dirPaths = List.of(HOME, DEV, TEST);

    public static WatchEvent.Kind[] watchKindEvents = new WatchEvent.Kind[] {
            StandardWatchEventKinds.ENTRY_CREATE,
            StandardWatchEventKinds.ENTRY_DELETE,
            StandardWatchEventKinds.ENTRY_MODIFY
    };

    public static String buildDirectoryPath(String directory) {
        return BASE + directory;
    }
}
