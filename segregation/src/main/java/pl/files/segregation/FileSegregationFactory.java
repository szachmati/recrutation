package pl.files.segregation;

import static pl.files.Utils.JAR;
import static pl.files.Utils.XML;

public class FileSegregationFactory {

    public static BaseFileSegregator create(String fileExtension) {
        switch (fileExtension) {
            case JAR:
                return new JarSegregator();
            case XML:
                return new XmlSegregator();
            default:
                throw new UnsupportedOperationException("File type + " + fileExtension + " not supported!");
        }
    }
}
