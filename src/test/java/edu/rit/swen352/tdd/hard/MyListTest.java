package edu.rit.swen352.tdd.hard;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Test suite for the {@link MyList} component.
 */
class MyListTest {

    @Test
    @DisplayName("Test that MyList is empty when created")
    void testEmptyList() {
        MyList<String> list = new MyList<>(10);
        assertTrue(list.isEmpty(), "List should be empty");
        assertTrue(list.size() == 0, "List size should be 0");
    }

    @Test
    @DisplayName("Test that MyList is not empty after adding an element")
    void testAddElement() {
        MyList<String> list = new MyList<>(10);
        assertTrue(list.add("Hello"), "Adding element should succeed");
        assertFalse(list.isEmpty(), "List should not be empty after adding an element");
        assertEquals(1, list.size(), "List size should be 1 after adding an element");
    }

    @Test
    @DisplayName("Test that Mylist does not allow null elements")
    void testAddNullElement() {
        MyList<String> list = new MyList<>(10);
        assertFalse(list.add(null), "Adding null element should fail");
        assertTrue(list.isEmpty(), "List should still be empty after trying to add null");
    }

    @Test
    @DisplayName("Test that MyList resizes when capacity is exceeded")
    void testResize() {
        MyList<Integer> list = new MyList<>(2);
        assertTrue(list.add(1), "Adding element should succeed");
        assertTrue(list.add(2), "Adding element should succeed");
        assertTrue(list.add(3), "Adding element should succeed, triggering resize");
        assertTrue(list.add(4), "Adding element should succeed");
        assertEquals(4, list.size(), "List size should be 4 after adding four elements");
    }

}
