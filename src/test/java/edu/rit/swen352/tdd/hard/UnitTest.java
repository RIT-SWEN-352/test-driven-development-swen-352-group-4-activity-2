package edu.rit.swen352.tdd.hard;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Test suite for the {@link Unit} helper component.
 */
class UnitTest {

  @Test
  @DisplayName("ctor : 1 : construct with dimension exponents, scale, and symbol")
  void ctor_1() {
    final Unit foot = new Unit(1, 0, 0, 0.3048, "ft");
    assertAll("foot unit"
      , () -> assertNotNull(foot)
      , () -> assertEquals(1, foot.getLengthExp(), "length exponent")
      , () -> assertEquals(0, foot.getTimeExp(), "time exponent")
      , () -> assertEquals(0, foot.getMassExp(), "mass exponent")
      , () -> assertEquals(0.3048, foot.getScaleToBase(), 1e-9, "scale to meters")
      , () -> assertEquals("ft", foot.getSymbol(), "symbol")
    );
  }

}
