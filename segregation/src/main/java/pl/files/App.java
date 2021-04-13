package pl.files;


public class App {

    public static void main(String[] args ) {
        DirectoryCreator.deleteDirectories();
        DirectoryCreator.createDirectories();
        FileWatcher.watchChanges();
    }
}
