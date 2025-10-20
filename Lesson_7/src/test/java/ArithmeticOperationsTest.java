import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class ArithmeticOperationsTest {
    @Test
    void testAdd() {
        assertEquals(5, ArithmeticOperations.add(2, 3));
        assertEquals(0, ArithmeticOperations.add(-2, 2));
    }

    @Test
    void testSubtract() {
        assertEquals(-1, ArithmeticOperations.subtract(2, 3));
        assertEquals(5, ArithmeticOperations.subtract(8, 3));
    }

    @Test
    void testMultiply() {
        assertEquals(6, ArithmeticOperations.multiply(2, 3));
        assertEquals(-6, ArithmeticOperations.multiply(2, -3));
    }

    @Test
    void testDivide() {
        assertEquals(2.5, ArithmeticOperations.divide(5, 2), 0.001);
        assertEquals(2.0, ArithmeticOperations.divide(8, 4), 0.001);
    }

    @Test
    void testDivisionByZero() {
        assertThrows(ArithmeticException.class, () -> ArithmeticOperations.divide(5, 0));
    }
}