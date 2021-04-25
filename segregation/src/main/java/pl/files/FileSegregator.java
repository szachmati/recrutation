package pl.files;

import org.apache.commons.io.FilenameUtils;
import pl.files.segregation.BaseFileSegregator;
import pl.files.segregation.FileSegregationFactory;

import java.nio.file.Path;
import java.nio.file.attribute.BasicFileAttributes;
import java.nio.file.attribute.FileTime;

public class FileSegregator {

    /**
     * if file extension is jar and creation date of file mod2 == 0 then move file to DEV folder
     * if file extension is jar and creation date of file mod2 != 0 then move file to TEST folder
     * if file extension is xml move file to dev folder
     */
    public static void segregateFiles(BasicFileAttributes fileAttributes, Path path) {
        FileTime fileTime = fileAttributes.creationTime();
        String fileExtension = FilenameUtils.getExtension(path.toString());
        BaseFileSegregator baseFileSegregator = FileSegregationFactory.create(fileExtension);

        baseFileSegregator.segregate(fileTime, path);

    }

}
