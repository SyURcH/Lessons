import org.testng.annotations.Test;
import static org.testng.Assert.*;

public class TriangleAreaTest {
    @Test
    public void testValidTriangle() {
        assertEquals(TriangleArea.calculateArea(3, 4, 5), 6.0, 0.001);
        assertEquals(TriangleArea.calculateArea(13, 14, 15), 84.0, 0.001);
    }

    @Test(expectedExceptions = IllegalArgumentException.class)
    public void testInvalidTriangle() {
        TriangleArea.calculateArea(1, 1, 3);
    }

    @Test(expectedExceptions = IllegalArgumentException.class)
    public void testNegativeSides() {
        TriangleArea.calculateArea(-1, 2, 3);
    }
}