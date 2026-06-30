package edu.rit.swen352.tdd.hard;

/**
 * A Measurement is a numeric value with a unit of measure.
 * Examples: 100kg, 5280ft, 47m^2, 55mph, and 9.8m/s^2.
 *
 * <p>
 *   The types of units must include length, time, and mass.
 *   Each type of unit must support multiple specific units,
 *   such as kilometers, miles, meters, feet, centimeters, and inches.
 *   You need to be able to convert between units,
 *   such as <strong>an inch is 2.54cm</strong>.
 *   Likewise for the other types of units: time and mass.
 * </p>
 *
 * <p>
 *   This must be an immutable
 *   <a href='https://en.wikipedia.org/wiki/Value_object'>Value Object</a>.
 *   Arithmetic operations must create new instances.
 * </p>
 */
public class Measurement {

  public Measurement(double value, Unit units) {
    throw new UnsupportedOperationException("NYI");
  }

  public double getValue() {
    throw new UnsupportedOperationException("NYI");
  }

  public Unit getUnits() {
    throw new UnsupportedOperationException("NYI");
  }

}
