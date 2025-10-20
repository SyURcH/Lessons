import org.testng.annotations.Test;
import static org.testng.Assert.*;

public class NumberComparatorTest {
    @Test
    public void testCompare() {
        assertEquals(NumberComparator.compare(3, 5), -1);
        assertEquals(NumberComparator.compare(5, 5), 0);
        assertEquals(NumberComparator.compare(8, 5), 1);
    }
}