import org.testng.annotations.Test;
import static org.testng.Assert.*;

public class ArithmeticOperationsTest {
    @Test
    public void testAdd() {
        assertEquals(ArithmeticOperations.add(2, 3), 5);
        assertEquals(ArithmeticOperations.add(-2, 2), 0);
    }

    @Test
    public void testSubtract() {
        assertEquals(ArithmeticOperations.subtract(2, 3), -1);
        assertEquals(ArithmeticOperations.subtract(8, 3), 5);
    }

    @Test
    public void testMultiply() {
        assertEquals(ArithmeticOperations.multiply(2, 3), 6);
        assertEquals(ArithmeticOperations.multiply(2, -3), -6);
    }

    @Test
    public void testDivide() {
        assertEquals(ArithmeticOperations.divide(5, 2), 2.5, 0.001);
        assertEquals(ArithmeticOperations.divide(8, 4), 2.0, 0.001);
    }

    @Test(expectedExceptions = ArithmeticException.class)
    public void testDivisionByZero() {
        ArithmeticOperations.divide(5, 0);
    }
}