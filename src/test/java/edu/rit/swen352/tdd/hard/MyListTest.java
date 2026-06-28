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

}
