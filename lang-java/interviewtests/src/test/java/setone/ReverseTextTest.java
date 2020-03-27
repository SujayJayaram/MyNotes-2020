package setone;

import static org.junit.jupiter.api.Assertions.*;

class ReverseTextTest {

    @org.junit.jupiter.api.Test
    void testReverseSimpleString() {
        assertEquals("FRED", ReverseText.reverse("DREF"));
    }


    @org.junit.jupiter.api.Test
    void testReverseEmptyString() {
        assertEquals("", ReverseText.reverse(""));
    }

}