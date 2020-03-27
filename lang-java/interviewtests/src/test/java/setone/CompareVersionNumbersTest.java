package setone;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CompareVersionNumbersTest {

    @Test
    void compare() {

        assertEquals(0, CompareVersionNumbers.compare(null, null));
        assertEquals(0, CompareVersionNumbers.compare("", null));
        assertEquals(0, CompareVersionNumbers.compare(null, ""));

        assertEquals(1, CompareVersionNumbers.compare("12", null));
        assertEquals(-1, CompareVersionNumbers.compare(null, "12"));

        assertEquals(0, CompareVersionNumbers.compare("12", "12"));
        assertEquals(0, CompareVersionNumbers.compare("12.1", "12.1"));
        assertEquals(0, CompareVersionNumbers.compare("12.1.2.3.4", "12.1.2.3.4"));

        assertEquals(1, CompareVersionNumbers.compare("12.1.6.7.3", "5.1"));
        assertEquals(-1, CompareVersionNumbers.compare("12.1", "18.1.5.3"));
    }
}