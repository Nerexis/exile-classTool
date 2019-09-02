package exile.classTool.model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

class ExileClassTest {

    @Test
    void testEquals() {
        ExileClass exileClass1 = new ExileClass("test", 0, 0, 0);
        ExileClass exileClass2 = new ExileClass("test", 1, 1, 1);
        assertEquals(exileClass1, exileClass2);
    }

    @Test
    void testNotEquals() {
        ExileClass exileClass1 = new ExileClass("test", 0, 0, 0);
        ExileClass exileClass2 = new ExileClass("test2", 1, 1, 1);
        assertNotEquals(exileClass1, exileClass2);
    }
}