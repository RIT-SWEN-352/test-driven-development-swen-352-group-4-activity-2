package edu.rit.swen352.tdd.hard;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Nested;
import static org.junit.jupiter.api.Assertions.*;

/**
 * Test suite for the {@link MySet} component.
 */
class MySetTest {
    @Test
    void anchorTest() {}
    @Nested
    @DisplayName("Constructor Tests")
    static class ConstructorTests {
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
    }
    

    @Nested
    @DisplayName("Add Method Tests")
    static class AddMethodTests {

        @Test
        @DisplayName("Test adding an element returns true when the element is not already in the set and false otherwise")
        public void testAddReturnsSuccessForNewElement() {
            MySet<Integer> set = new MySet<>(10);
            assertTrue(set.add(1), "Adding a new element should return true");
            assertFalse(set.add(1), "Adding a duplicate element should return false");
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
            assertFalse(set.add(1), "Adding a duplicate element should not increase size");
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
    }
  
  
    @Nested
    @DisplayName("Contains Method Tests")
    static class ContainsMethodTests {
        @Test
        @DisplayName("Test contains method")
        public void testContains() {
            //setup
            MySet<Integer> set = new MySet<>(10);
            set.add(1);
            set.add(2);
            //testing
            assertAll(
                () -> assertTrue(set.contains(1), "Set should contain 1"),
                () -> assertTrue(set.contains(2), "Set should contain 2"),
                () -> assertFalse(set.contains(3), "Set should not contain 3")
            );
        }

        @Test
        @DisplayName("Test contains after removing an element")
        public void testContainsAfterRemove() {
            //setup
            MySet<Integer> set = new MySet<>(10);
            set.add(1);
            set.add(2);
            //testing
            assertTrue(set.contains(1), "Set should contain 1");
            assertTrue(set.contains(2), "Set should contain 2");
            assertTrue(set.remove(1), "Removing an element should return true");
            assertFalse(set.contains(1), "Set should not contain 1 after removal");
            assertTrue(set.contains(2), "Set should still contain 2 after removal of 1");
        }

        @Test
        @DisplayName("Test contains method with null element throws exception")
        public void testContainsNullThrowsException() {
            MySet<Integer> set = new MySet<>(10);
            set.add(1);
            set.add(2);

            assertThrows(IllegalArgumentException.class, () -> set.contains(null), "Contains with a null element should throw IllegalArgumentException");
            assertAll(
                    () -> assertEquals(2, set.size(), "Size should remain unchanged after contains with null"),
                    () -> assertTrue(set.contains(1), "Set should still contain 1 after contains with null"),
                    () -> assertTrue(set.contains(2), "Set should still contain 2 after contains with null")
            );
        }
    }

    @Nested
    @DisplayName("Remove Method Tests")
    static class RemoveMethodTests {
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
        @DisplayName("Test removing twice does not change size after first removal")
        public void testRemoveTwiceDoesNotChangeSize() {
            //setup
            MySet<Integer> set = new MySet<>(10);
            set.add(1);
            set.add(2);
            set.add(3);
            //testing
            assertEquals(3, set.size(), "Adding an element should increase size to 3");
            assertTrue(set.remove(2), "Removing an element should return true");
            assertEquals(2, set.size(), "Removing an element should decrease size to 2");
            assertFalse(set.remove(2), "Removing the same element again should return false");
            assertEquals(2, set.size(), "Removing the same element again should not change size");
        }
    }
 
    @Nested
    @DisplayName("Map Method Tests")
    static class MapMethodTests {
        @Test
        @DisplayName("Test map method with a simple function")
        public void testMap() {
            //setup
            MySet<Integer> set = new MySet<>(10);
            set.add(1);
            set.add(2);
            //testing
            MySet<String> mappedSet = set.map(Object::toString);
            assertAll(
                () -> assertEquals(2, mappedSet.size(), "Mapped set should have size 2"),
                () -> assertTrue(mappedSet.contains("1"), "Mapped set should contain '1'"),
                () -> assertTrue(mappedSet.contains("2"), "Mapped set should contain '2'")
            );
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
            assertAll(
                () -> assertEquals(2, mappedSet.size(), "Mapped set should have size 2 due to overlapping elements"),
                () -> assertTrue(mappedSet.contains("even"), "Mapped set should contain 'even'"),
                () -> assertTrue(mappedSet.contains("odd"), "Mapped set should contain 'odd'")
            );
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


    @Test
    @DisplayName("Test dynamic resizing of the set when adding elements beyond initial capacity")
    public void testDynamicResizing() {
        MySet<Integer> set = new MySet<>(2);
        set.add(1);
        set.add(2);
        assertTrue(set.add(3), "Adding an element beyond initial capacity should succeed");
        assertTrue(set.add(4), "Adding an element beyond initial capacity should succeed");
        assertAll(
            () -> assertTrue(set.contains(3), "Set should have 3 after dynamic resizing"),
            () -> assertTrue(set.contains(4), "Set should have 4 after dynamic resizing"),
            () -> assertEquals(4, set.size(), "Set should have size 4 after dynamic resizing")
        );
    }

}
