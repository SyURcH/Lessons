import org.testng.annotations.Test;
import static org.testng.Assert.*;

public class FactorialCalculatorTest {
    @Test
    public void testFactorial() {
        assertEquals(FactorialCalculator.factorial(0), 1);
        assertEquals(FactorialCalculator.factorial(1), 1);
        assertEquals(FactorialCalculator.factorial(5), 120);
    }

    @Test(expectedExceptions = IllegalArgumentException.class)
    public void testFactorialNegative() {
        FactorialCalculator.factorial(-1);
    }
}