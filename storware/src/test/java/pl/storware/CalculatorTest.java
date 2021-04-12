package pl.storware;

import org.junit.jupiter.api.Test;


import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class CalculatorTest {

    private final Calculator calculator = new Calculator();

    @Test
    public void givenFilePerformCalculations() {
        //given
        String filePath = "operationTest.txt";
        //when
        double result = calculator.calculate(filePath);
        //then
        assertEquals(32, result);
    }

    @Test
    public void givenInvalidFilePathShouldThrowNullPointerException() {
        //given
        String filePath = ".//.//a/sdsa";
        assertThrows(NullPointerException.class, () -> calculator.calculate(filePath));
    }
}
