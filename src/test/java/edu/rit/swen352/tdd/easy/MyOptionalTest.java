package edu.rit.swen352.tdd.easy;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Test suite for the {@link MyOptional} component.
 */
class MyOptionalTest {
    @Test
    @DisplayName("Test that MyOptional is empty when created with empty()")
    void testEmptyOptional() {
        MyOptional<String> optional = MyOptional.empty();
        assertFalse(optional.isPresent(), "Optional should be empty");
    }

    @Test
    @DisplayName("Test that MyOptional is present when created with of()")
    void testNonEmptyOptional() {
        MyOptional<String> optional = MyOptional.of("Hello");
        assertTrue(optional.isPresent(), "Optional should be present");
    }

    @Test
    @DisplayName("Test that of throws IllegalArgumentException when created with null value")
    void testNullValue() {
        assertThrows(IllegalArgumentException.class, () -> MyOptional.of(null));
    }

    @Test
    @DisplayName("Test that MyOptional is empty when created with ofNullable(null)")
    void testOfNullableWithNull() {
        MyOptional<String> optional = MyOptional.ofNullable(null);
        assertFalse(optional.isPresent(), "Optional should be empty when created with ofNullable(null)");
    }

    @Test
    @DisplayName("Test that MyOptional is present when created with ofNullable(non-null)")
    void testOfNullableWithNonNull() {
        MyOptional<String> optional = MyOptional.ofNullable("Hello");
        assertTrue(optional.isPresent(), "Optional should be present when created with ofNullable(non-null)");
    }

    @Test
    @DisplayName("Test that get() returns the correct value when present")
    void testGetValue() {
        MyOptional<String> optional = MyOptional.of("Hello");
        assertEquals("Hello", optional.get(), "get() should return the correct value when present");
    }

    @Test
    @DisplayName("Test that get() throws NoSuchElementException when empty")
    void testGetValueWhenEmpty() {
        MyOptional<String> optional = MyOptional.empty();
        assertThrows(java.util.NoSuchElementException.class, () -> optional.get(), "get() should throw NoSuchElementException when empty");
    }

    @Test
    @DisplayName("Test that ifPresent() executes the consumer when value is present")
    void testIfPresentWithValue() {
        MyOptional<String> optional = MyOptional.of("Hello");
        StringBuilder result = new StringBuilder();
        optional.ifPresent(value -> result.append(value));
        assertEquals("Hello", result.toString(), "ifPresent() should execute the consumer when value is present");
    }

    @Test
    @DisplayName("Test that ifPresent() does not execute the consumer when value is not present")
    void testIfPresentWithoutValue() {
        MyOptional<String> optional = MyOptional.empty();
        StringBuilder result = new StringBuilder();
        optional.ifPresent(value -> result.append(value).append("I should not be executed"));
        assertEquals("", result.toString(), "ifPresent() should not execute the consumer when value is not present");
    }
}
