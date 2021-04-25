package pl.files;

import org.apache.commons.io.FilenameUtils;
import pl.files.strategy.BaseFileSegregator;
import pl.files.strategy.JarSegregator;
import pl.files.strategy.XmlSegregator;
import java.nio.file.Path;
import java.nio.file.attribute.BasicFileAttributes;
import java.nio.file.attribute.FileTime;

import static pl.files.Utils.*;

public class FileSegregator {

    /**
     * if file extension is jar and creation date of file mod2 == 0 then move file to DEV folder
     * if file extension is jar and creation date of file mod2 != 0 then move file to TEST folder
     * if file extension is xml move file to dev folder
     */
    public static void segregateFiles(BasicFileAttributes fileAttributes, Path path) {
        FileTime fileTime = fileAttributes.creationTime();
        String fileExtension = FilenameUtils.getExtension(path.toString());
        BaseFileSegregator baseFileSegregator = getSegregator(fileExtension);

        baseFileSegregator.segregate(fileTime, path);

    }

    private static BaseFileSegregator getSegregator(String fileExtension) {
        BaseFileSegregator segregator = null;
        switch (fileExtension) {
            case JAR:
                segregator = new JarSegregator();
                break;
            case XML:
                segregator = new XmlSegregator();
                break;
        }
        return segregator;
    }

}
