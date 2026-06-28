package edu.rit.swen352.tdd.hard;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

import java.util.NoSuchElementException;

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

    @Test
    @DisplayName("Test that MyList gets elements correctly")
    void testGetElement() {
        MyList<String> list = new MyList<>(10);
        list.add("Hello");
        list.add("World");
        assertAll(
            () -> assertEquals("Hello", list.get(0), "First element should be 'Hello'"),
            () -> assertEquals("World", list.get(1), "Second element should be 'World'")
        );
    }

    @Test
    @DisplayName("Test that get throws exception for out-of-bounds index")
    void testGetOutOfBoundsThrowsException() {
        MyList<String> list = new MyList<>(10);
        list.add("1");
        assertThrows(NoSuchElementException.class, () -> list.get(2), "get should throw a NoSuchElementException when index is out of bounds" );
    }

    @Test
    @DisplayName("Test that remove correctly removes elements")
    void testRemoveElement() {
        MyList<Integer> list = new MyList<>(10);
        list.add(1);
        list.add(2);
        list.add(3);
        assertEquals(2, list.remove(1), "remove should return the removed element (2)");
        assertAll(
            () -> assertEquals(3, list.get(1), "After removing the element at index 2 the remainings elements (3) move to fill the gap"),
            () -> assertEquals(2, list.size(), "after removing an element, the size should be reduced by 1")
        );
    }

    @Test
    @DisplayName("Test that remove throw exception for out-of-bounds index")
    void testRemoveOutOfBoundsThrowsException() {
        MyList<String> list = new MyList<>(10);
        list.add("1");
        assertThrows(NoSuchElementException.class, () -> list.remove(4), "get should throw a NoSuchElementException when index is out of bounds" );
    
    }

    @Test
    @DisplayName("Test that removeFirst correctly removes only the first instance of the provided element")
    void testRemoveFirstElement() {
        MyList<String> list = new MyList<>(10);
        list.add("1");
        list.add("2");
        list.add("3");
        list.add("2");
        list.add("4");

        assertTrue(list.removeFirst("2"), "removeFirst should return true when it succeeds");
        assertAll(
            () -> assertEquals("3", list.get(1), "After removing the first instance of the element the remainings elements move to fill the gap"),
            () -> assertEquals(4, list.size(), "after removing an element, the size should be reduced by 1"),
            () -> assertEquals("2", list.get(2), "The second instance of the value should remain")
        );
    }

}