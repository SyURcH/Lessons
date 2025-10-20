import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class NumberComparatorTest {
    @Test
    void testCompare() {
        assertEquals(-1, NumberComparator.compare(3, 5));
        assertEquals(0, NumberComparator.compare(5, 5));
        assertEquals(1, NumberComparator.compare(8, 5));
    }
}