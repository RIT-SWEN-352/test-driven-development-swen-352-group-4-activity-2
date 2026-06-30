package edu.rit.swen352.tdd.hard;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Test suite for the {@link Measurement} component.
 */
class MeasurementTest {

  @Test
  @DisplayName("ctor : 1 : construct with value and units")
  void ctor_1() {
    final Measurement m = new Measurement(5.0, Unit.METER);
    assertAll("5 meters"
      , () -> assertNotNull(m)
      , () -> assertEquals(5.0, m.getValue(), 1e-9, "value")
      , () -> assertEquals(Unit.METER, m.getUnits(), "units")
    );
  }

  @Test
  @DisplayName("ctor : 2 : reject null units (requirements gap)")
  void ctor_2_fail() {
    assertThrows(IllegalArgumentException.class,
        () -> new Measurement(5.0, null));
  }

}
