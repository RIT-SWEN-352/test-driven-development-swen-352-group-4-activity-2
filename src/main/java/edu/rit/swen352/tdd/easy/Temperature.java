package edu.rit.swen352.tdd.easy;

/**
 * A temperature measurement.
 *
 * <p>
 * This must be an immutable
 * <a href='https://en.wikipedia.org/wiki/Value_object'>Value Object</a>.
 * Conversions must create new instances.
 * </p>
 *
 * <p>
 * You must implement these features:
 * <ul>
 *   <li>constructor:
 *     <ul>
 *       <li>a ctor that supplies both a value, as {@code double}, and a {@linkplain TemperatureUnit unit}</li>
 *       <li>another ctor with just a value; unit is defaulted to {@link TemperatureUnit#CELSIUS}</li>
 *     </ul>
 *   </li>
 *   <li>{@code getValue()}: returns the temperature value in the current units</li>
 *   <li>{@code getUnit()}: return the current temperature units</li>
 *   <li>{@code convertTo(unit)}: create a new Temperature in the new unit</li>
 *   <li>{@code toString()}: returns a human-friendly representation of the temperature, eg 25°C or 25K</li>
 * </ul>
 */
public class Temperature {
  public enum TemperatureUnit {
    CELSIUS, FAHRENHEIT, KELVIN
  }

  double value;
  TemperatureUnit unit;

  public Temperature(double value, TemperatureUnit unit) {
    this.value = value;
    this.unit = unit;
  }

  public Temperature(double value) {
    this(value, TemperatureUnit.CELSIUS);
  }

  public double getValue() {
    return value;
  }

  public TemperatureUnit getUnit() {
    return unit;
  }

  public Temperature convertTo(TemperatureUnit newUnit) {
    return new Temperature(this.value, newUnit);
  }

}

