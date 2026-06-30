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

  private final double value;
  private final Unit units;

  static final String NULL_UNITS_ERROR = "units must not be null";
  static final String INCOMPATIBLE_UNITS_ERROR = "units are not compatible";

  public Measurement(double value, Unit units) {
    if (units == null) {
      throw new IllegalArgumentException(NULL_UNITS_ERROR);
    }
    this.value = value;
    this.units = units;
  }

  public double getValue() {
    return value;
  }

  public Unit getUnits() {
    return units;
  }

  public Measurement convertTo(Unit target) {
    if (!units.isCompatible(target)) {
      throw new IllegalArgumentException(INCOMPATIBLE_UNITS_ERROR);
    }
    final double convertedValue = value * units.getScaleToBase() / target.getScaleToBase();
    return new Measurement(convertedValue, target);
  }

  public Measurement addition(Measurement other) {
    final Measurement rhs = other.convertTo(units);
    return new Measurement(value + rhs.value, units);
  }

  public Measurement substraction(Measurement other) {
    final Measurement rhs = other.convertTo(units);
    return new Measurement(value - rhs.value, units);
  }

  public Measurement multiplication(double scalar) {
    return new Measurement(value * scalar, units);
  }

  public Measurement multiplication(Measurement other) {
    return new Measurement(value * other.value, units.multiply(other.units));
  }

  public Measurement division(double scalar) {
    return new Measurement(value / scalar, units);
  }

}
