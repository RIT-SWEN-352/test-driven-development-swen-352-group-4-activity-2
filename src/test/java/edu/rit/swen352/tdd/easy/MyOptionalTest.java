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
    
}
