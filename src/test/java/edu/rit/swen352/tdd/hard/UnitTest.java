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

  @Test
  @DisplayName("multiply : 4 : combines dimensions, scales, and symbols")
  void multiply_4() {
    final Unit meter = new Unit(1, 0, 0, 1.0, "m");
    final Unit second = new Unit(0, 1, 0, 1.0, "s");
    final Unit product = meter.multiply(second);
    assertAll("meter * second"
      , () -> assertEquals(1, product.getLengthExp())
      , () -> assertEquals(1, product.getTimeExp())
      , () -> assertEquals(0, product.getMassExp())
      , () -> assertEquals(1.0, product.getScaleToBase(), 1e-9)
      , () -> assertEquals("m*s", product.getSymbol())
    );
  }

  @Test
  @DisplayName("divide : 5 : subtracts dimensions, divides scales, builds quotient symbol")
  void divide_5() {
    final Unit meter = new Unit(1, 0, 0, 1.0, "m");
    final Unit second = new Unit(0, 1, 0, 1.0, "s");
    final Unit velocity = meter.divide(second);
    assertAll("m / s = m/s (velocity)"
      , () -> assertEquals(1, velocity.getLengthExp())
      , () -> assertEquals(-1, velocity.getTimeExp())
      , () -> assertEquals(0, velocity.getMassExp())
      , () -> assertEquals(1.0, velocity.getScaleToBase(), 1e-9)
      , () -> assertEquals("m/s", velocity.getSymbol())
    );
  }

  @Test
  @DisplayName("constants : 6 : SI base units and DIMENSIONLESS are predefined")
  void constants_6() {
    assertAll("base SI units"
      , () -> assertEquals(1.0, Unit.METER.getScaleToBase(), 1e-9)
      , () -> assertEquals(1, Unit.METER.getLengthExp())
      , () -> assertEquals("m", Unit.METER.getSymbol())
      , () -> assertEquals(1, Unit.SECOND.getTimeExp())
      , () -> assertEquals("s", Unit.SECOND.getSymbol())
      , () -> assertEquals(1, Unit.KILOGRAM.getMassExp())
      , () -> assertEquals("kg", Unit.KILOGRAM.getSymbol())
      , () -> assertEquals(0, Unit.DIMENSIONLESS.getLengthExp())
      , () -> assertEquals("", Unit.DIMENSIONLESS.getSymbol())
    );
  }

}
