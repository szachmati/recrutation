package pl.storware;


public class App {

    private static final String FILE_PATH = "operations.txt";

    public static void main(String[] args ) {
        Calculator c = new Calculator();
        c.calculate(FILE_PATH);
    }
}
