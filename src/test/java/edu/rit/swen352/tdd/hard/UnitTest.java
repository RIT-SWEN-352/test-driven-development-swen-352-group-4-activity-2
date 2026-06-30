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

  @Test
  @DisplayName("equals : 2 : same dims and scale are equal; different scale or dim is not")
  void equals_2() {
    final Unit footA = new Unit(1, 0, 0, 0.3048, "ft");
    final Unit footB = new Unit(1, 0, 0, 0.3048, "ft");
    final Unit meter = new Unit(1, 0, 0, 1.0, "m");
    final Unit second = new Unit(0, 1, 0, 1.0, "s");
    assertAll("equality contract"
      , () -> assertEquals(footA, footB, "structurally identical units are equal")
      , () -> assertEquals(footA.hashCode(), footB.hashCode(), "consistent hashCode")
      , () -> assertNotEquals(footA, meter, "different scale -> not equal")
      , () -> assertNotEquals(meter, second, "different dimensions -> not equal")
    );
  }

  @Test
  @DisplayName("isCompatible : 3 : same dimensions are compatible regardless of scale")
  void isCompatible_3() {
    final Unit meter = new Unit(1, 0, 0, 1.0, "m");
    final Unit foot = new Unit(1, 0, 0, 0.3048, "ft");
    final Unit second = new Unit(0, 1, 0, 1.0, "s");
    final Unit mps = new Unit(1, -1, 0, 1.0, "m/s");
    assertAll("dimensional compatibility"
      , () -> assertTrue(meter.isCompatible(foot), "meter and foot are both length")
      , () -> assertTrue(meter.isCompatible(meter), "reflexive")
      , () -> assertFalse(meter.isCompatible(second), "length not compatible with time")
      , () -> assertFalse(meter.isCompatible(mps), "length not compatible with velocity")
    );
  }

}
