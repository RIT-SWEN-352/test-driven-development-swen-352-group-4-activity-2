package edu.rit.swen352.tdd.hard;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

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

  static Stream<Arguments> conversionCases() {
    return Stream.of(
        Arguments.of(1.0, Unit.INCH, Unit.CENTIMETER, 2.54),
        Arguments.of(5280.0, Unit.FOOT, Unit.MILE, 1.0),
        Arguments.of(1.0, Unit.MILE, Unit.KILOMETER, 1.609344),
        Arguments.of(1.0, Unit.HOUR, Unit.MINUTE, 60.0),
        Arguments.of(1.0, Unit.POUND, Unit.KILOGRAM, 0.45359237),
        Arguments.of(100.0, Unit.METER, Unit.METER, 100.0)
    );
  }

  @ParameterizedTest(name = "{0} {1} -> {3} {2}")
  @MethodSource("conversionCases")
  @DisplayName("convertTo : 3 : converts value across compatible units")
  void convertTo_3(double value, Unit from, Unit to, double expected) {
    final Measurement source = new Measurement(value, from);
    final Measurement converted = source.convertTo(to);
    assertAll("conversion result"
      , () -> assertEquals(expected, converted.getValue(), 1e-6, "converted value")
      , () -> assertEquals(to, converted.getUnits(), "target units")
      , () -> assertEquals(value, source.getValue(), 1e-9, "source is immutable")
    );
  }

  @Test
  @DisplayName("convertTo : 4 : reject conversion across incompatible units")
  void convertTo_4_fail() {
    final Measurement fiveMeters = new Measurement(5.0, Unit.METER);
    final Exception e = assertThrows(IllegalArgumentException.class,
        () -> fiveMeters.convertTo(Unit.SECOND));
    assertEquals(Measurement.INCOMPATIBLE_UNITS_ERROR, e.getMessage());
  }

  @Test
  @DisplayName("addition : 5 : same units sum values, result in left-hand units")
  void addition_5() {
    final Measurement a = new Measurement(3.0, Unit.METER);
    final Measurement b = new Measurement(2.0, Unit.METER);
    final Measurement sum = a.addition(b);
    assertAll("3m + 2m = 5m"
      , () -> assertEquals(5.0, sum.getValue(), 1e-9)
      , () -> assertEquals(Unit.METER, sum.getUnits())
      , () -> assertEquals(3.0, a.getValue(), 1e-9, "lhs is immutable")
    );
  }

  @Test
  @DisplayName("addition : 6 : compatible units convert RHS to LHS units")
  void addition_6() {
    final Measurement meters = new Measurement(1.0, Unit.METER);
    final Measurement feet = new Measurement(1.0, Unit.FOOT);
    final Measurement sum = meters.addition(feet);
    assertAll("1m + 1ft = 1.3048m"
      , () -> assertEquals(1.3048, sum.getValue(), 1e-6)
      , () -> assertEquals(Unit.METER, sum.getUnits())
    );
  }

  @Test
  @DisplayName("addition : 7 : reject incompatible units")
  void addition_7_fail() {
    final Measurement meters = new Measurement(1.0, Unit.METER);
    final Measurement seconds = new Measurement(1.0, Unit.SECOND);
    final Exception e = assertThrows(IllegalArgumentException.class,
        () -> meters.addition(seconds));
    assertEquals(Measurement.INCOMPATIBLE_UNITS_ERROR, e.getMessage());
  }

  @Test
  @DisplayName("substraction : 8 : subtract compatible measurements in LHS units")
  void substraction_8() {
    final Measurement oneMile = new Measurement(1.0, Unit.MILE);
    final Measurement oneKm = new Measurement(1.0, Unit.KILOMETER);
    final Measurement diff = oneMile.substraction(oneKm);
    assertAll("1mi - 1km ~ 0.378mi"
      , () -> assertEquals(0.378, diff.getValue(), 1e-3)
      , () -> assertEquals(Unit.MILE, diff.getUnits())
    );
  }

  @Test
  @DisplayName("multiplication : 9 : scalar scales value, preserves units")
  void multiplication_9() {
    final Measurement five = new Measurement(5.0, Unit.METER);
    final Measurement tripled = five.multiplication(3.0);
    assertAll("5m * 3 = 15m"
      , () -> assertEquals(15.0, tripled.getValue(), 1e-9)
      , () -> assertEquals(Unit.METER, tripled.getUnits())
    );
  }

  @Test
  @DisplayName("multiplication : 10 : Measurement RHS combines values and units")
  void multiplication_10() {
    final Measurement force = new Measurement(2.0, Unit.METER);
    final Measurement t = new Measurement(3.0, Unit.SECOND);
    final Measurement product = force.multiplication(t);
    assertAll("2m * 3s = 6 m*s"
      , () -> assertEquals(6.0, product.getValue(), 1e-9)
      , () -> assertEquals(1, product.getUnits().getLengthExp())
      , () -> assertEquals(1, product.getUnits().getTimeExp())
      , () -> assertEquals("m*s", product.getUnits().getSymbol())
    );
  }

  @Test
  @DisplayName("division : 11 : scalar divides value, preserves units")
  void division_11() {
    final Measurement ten = new Measurement(10.0, Unit.METER);
    final Measurement half = ten.division(4.0);
    assertAll("10m / 4 = 2.5m"
      , () -> assertEquals(2.5, half.getValue(), 1e-9)
      , () -> assertEquals(Unit.METER, half.getUnits())
    );
  }

  @Test
  @DisplayName("division : 12 : reject divide by zero scalar (requirements gap)")
  void division_12_fail() {
    final Measurement ten = new Measurement(10.0, Unit.METER);
    final Exception e = assertThrows(ArithmeticException.class,
        () -> ten.division(0.0));
    assertEquals(Measurement.DIVIDE_BY_ZERO_ERROR, e.getMessage());
  }

  @Test
  @DisplayName("division : 13 : same-unit Measurement RHS yields dimensionless scalar")
  void division_13() {
    final Measurement six = new Measurement(6.0, Unit.METER);
    final Measurement two = new Measurement(2.0, Unit.METER);
    final Measurement ratio = six.division(two);
    assertAll("6m / 2m = 3 (dimensionless)"
      , () -> assertEquals(3.0, ratio.getValue(), 1e-9)
      , () -> assertEquals(0, ratio.getUnits().getLengthExp())
      , () -> assertEquals(0, ratio.getUnits().getTimeExp())
      , () -> assertEquals(0, ratio.getUnits().getMassExp())
    );
  }

  @Test
  @DisplayName("division : 14 : different-unit Measurement RHS yields compound unit")
  void division_14() {
    final Measurement distance = new Measurement(100.0, Unit.METER);
    final Measurement time = new Measurement(4.0, Unit.SECOND);
    final Measurement velocity = distance.division(time);
    assertAll("100m / 4s = 25 m/s"
      , () -> assertEquals(25.0, velocity.getValue(), 1e-9)
      , () -> assertEquals(1, velocity.getUnits().getLengthExp())
      , () -> assertEquals(-1, velocity.getUnits().getTimeExp())
      , () -> assertEquals("m/s", velocity.getUnits().getSymbol())
    );
  }

}
