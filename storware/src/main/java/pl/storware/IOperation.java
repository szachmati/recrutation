package pl.storware;

public interface IOperation {

    default double add(double a, int b) {
        return a + b;
    }
    default double subtract(double a, int b) {
        return a - b;
    }
    default double divide(double a, int b) {
        return a / b;
    }
    default double multiply(double a, int b) {
        return a * b;
    }
}
