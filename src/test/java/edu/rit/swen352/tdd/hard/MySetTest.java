package edu.rit.swen352.tdd.hard;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Test suite for the {@link MySet} component.
 */
class MySetTest {
    @Test
    @DisplayName("Test new set with 0 starting capacity throws exception")
    public void testSetWithZeroCapacityThrowsException() {
        assertThrows(IllegalArgumentException.class, () -> new MySet<>(0), "New set with 0 starting capacity should throw IllegalArgumentException");
    }

    @Test
    @DisplayName("Test new set with negative starting capacity throws exception")
    public void testSetWithNegativeCapacityThrowsException() {
        assertThrows(IllegalArgumentException.class, () -> new MySet<>(-1), "New set with negative starting capacity should throw IllegalArgumentException");
    }

    @Test
    @DisplayName("Test new set isEmpty")
    public void testNewSetIsEmpty() {
        MySet<Integer> set = new MySet<>(10);
        assertTrue(set.isEmpty(), "Newly created MySet should be empty");
    }

    @Test
    @DisplayName("Test new set has size 0")
    public void testNewSetSize() {
        MySet<Integer> set = new MySet<>(10);
        assertEquals(0, set.size(), "Newly created MySet should have size 0");
    }

    @Test
    @DisplayName("Test adding an element increases size")
    public void testAddIncreasesSize() {
        MySet<Integer> set = new MySet<>(10);
        assertEquals(0, set.size(), "Adding an element should increase size to 1");
        set.add(1);
        assertEquals(1, set.size(), "Adding an element should increase size to 1");
        set.add(2);
        assertEquals(2, set.size(), "Adding an element should increase size to 2");
        set.add(3);
        assertEquals(3, set.size(), "Adding an element should increase size to 3");
        set.add(4);
        assertEquals(4, set.size(), "Adding an element should increase size to 4");
    }

    @Test
    @DisplayName("Test adding duplicate elements does not increase size")
    public void testAddDuplicateDoesNotIncreaseSize() {
        //setup
        MySet<Integer> set = new MySet<>(10);
        set.add(1);
        set.add(2);
        //testing
        assertEquals(2, set.size(), "Adding an element should increase size to 2");
        set.add(1);
        assertEquals(2, set.size(), "Adding a duplicate element should not increase size");
    }

    @Test
    @DisplayName("Test adding null element throws exception")
    public void testAddNullThrowsException() {
        //setup
        MySet<Integer> set = new MySet<>(10);
        //testing
        assertThrows(IllegalArgumentException.class, () -> set.add(null), "Adding a null element should throw IllegalArgumentException");
    }

    @Test
    @DisplayName("Test removing an element decreases size")
    public void testRemoveDecreasesSize() {
        //setup
        MySet<Integer> set = new MySet<>(10);
        set.add(1);
        set.add(2);
        set.add(3);
        //testing
        assertEquals(3, set.size(), "Adding an element should increase size to 3");
        assertTrue(set.remove(2), "Removing an element should return true");
        assertEquals(2, set.size(), "Removing an element should decrease size to 2");
    }

    @Test
    @DisplayName("Test removing an element that does not exist does not change size")
    public void testRemoveNonExistentDoesNotChangeSize() {
        //setup
        MySet<Integer> set = new MySet<>(10);
        set.add(1);
        set.add(2);
        set.add(3);
        //testing
        assertEquals(3, set.size(), "Adding an element should increase size to 3");
        assertFalse(set.remove(4), "Removing a non-existent element should return false");
        assertEquals(3, set.size(), "Removing a non-existent element should not change size");
    }

    @Test
    @DisplayName("Test contains method")
    public void testContains() {
        //setup
        MySet<Integer> set = new MySet<>(10);
        set.add(1);
        set.add(2);
        //testing
        assertTrue(set.contains(1), "Set should contain 1");
        assertTrue(set.contains(2), "Set should contain 2");
        assertFalse(set.contains(3), "Set should not contain 3");
    }

    @Test
    @DisplayName("Test contains method with null element throws exception")
    public void testContainsNullThrowsException() {
        MySet<Integer> set = new MySet<>(10);
        set.add(1);
        set.add(2);

        assertThrows(IllegalArgumentException.class, () -> set.contains(null), "Contains with a null element should throw IllegalArgumentException");
    }

    @Test
    @DisplayName("Test map method with a simple function")
    public void testMap() {
        //setup
        MySet<Integer> set = new MySet<>(10);
        set.add(1);
        set.add(2);
        //testing
        MySet<String> mappedSet = set.map(Object::toString);
        assertEquals(2, mappedSet.size(), "Mapped set should have size 2");
        assertTrue(mappedSet.contains("1"), "Mapped set should contain '1'");
        assertTrue(mappedSet.contains("2"), "Mapped set should contain '2'");
    }

    @Test
    @DisplayName("Test map method with an empty set returns an empty set")
    public void testMapEmptySet() {
        MySet<Integer> set = new MySet<>(10);
        MySet<String> mappedSet = set.map(Object::toString);
        assertTrue(mappedSet.isEmpty(), "Mapped set of an empty set should be empty");
    }

    @Test
    @DisplayName("Test map with overlapping elements in the mapped set")
    public void testMapWithOverlappingElements() {
        MySet<Integer> set = new MySet<>(10);
        set.add(1);
        set.add(2);
        set.add(3);

        // Map to even/odd strings
        MySet<String> mappedSet = set.map(i -> (i % 2 == 0) ? "even" : "odd");

        assertEquals(2, mappedSet.size(), "Mapped set should have size 2 due to overlapping elements");
        assertTrue(mappedSet.contains("even"), "Mapped set should contain 'even'");
        assertTrue(mappedSet.contains("odd"), "Mapped set should contain 'odd'");
    }

    @Test
    @DisplayName("Test map method with null function throws exception")
    public void testMapNullFunctionThrowsException() {
        MySet<Integer> set = new MySet<>(10);
        set.add(1);
        set.add(2);

        assertThrows(IllegalArgumentException.class, () -> set.map(null), "Mapping with a null function should throw IllegalArgumentException");
    }

    




}
