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
    }

}
