package test;

import model.Item;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

// Represents the tests for Item
class ItemTest {
    private Item testItem1;
    private Item testItem2;

    @BeforeEach
    void runBefore() {
        testItem1 = new Item("apple", 200, "A");
        testItem2 = new Item("pear", 300, "B");
    }

    @Test
    void testConstructor() {
        assertEquals("apple", testItem1.getName());
        assertEquals(200, testItem1.getQuantity());
        assertEquals("A", testItem1.getLocation());
        assertTrue(testItem1.getQuantity() > 0);

        assertEquals("pear", testItem2.getName());
        assertEquals(300, testItem2.getQuantity());
        assertEquals("B", testItem2.getLocation());
        assertTrue(testItem2.getQuantity() > 0);
    }

    @Test
    void testGetName() {
        assertEquals("apple", testItem1.getName());
        assertEquals("pear", testItem2.getName());
    }

    @Test
    void testGetQuantity() {
        assertEquals(200, testItem1.getQuantity());
        assertEquals(300, testItem2.getQuantity());
    }

    @Test
    void testGetLocation() {
        assertEquals("A", testItem1.getLocation());
        assertEquals("B", testItem2.getLocation());
    }

    @Test
    void testAddQuantity() {
       testItem1.addQuantity(100);
       testItem2.addQuantity(300);
       assertEquals(300, testItem1.getQuantity());
       assertEquals(600, testItem2.getQuantity());
    }

    @Test
    void testMultipleAddQuantity() {
        testItem1.addQuantity(100);
        testItem1.addQuantity(300);

        assertEquals(600, testItem1.getQuantity());
    }

    @Test
    void testReduceQuantity() {
        testItem1.reduceQuantity(100);
        testItem1.reduceQuantity(50);
        testItem2.reduceQuantity(200);
        assertEquals(50, testItem1.getQuantity());
        assertEquals(100, testItem2.getQuantity());
    }

    @Test
    void testMultipleReduceQuantity() {
        testItem1.reduceQuantity(100);
        testItem1.reduceQuantity(50);

        assertEquals(300, testItem2.getQuantity());
    }

    @Test
    void testIsNoMore() {
        assertTrue(testItem1.isNoMore(200));
        assertTrue(testItem2.isNoMore(300));

        assertFalse(testItem1.isNoMore(400));
        assertFalse(testItem2.isNoMore(400));

        assertFalse(testItem2.isNoMore(100));
        assertFalse(testItem2.isNoMore(100));
    }

    @Test
    void  testIsEnough() {
        assertTrue(testItem1.isEnough(100));
        assertTrue(testItem2.isEnough(100));

        assertFalse(testItem1.isEnough(500));
        assertFalse(testItem2.isEnough(700));
    }
}
