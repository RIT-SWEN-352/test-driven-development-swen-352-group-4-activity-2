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
    if (unit == newUnit) {
      return new Temperature(value, unit);
    }

    double newValue = 0;

    switch (unit) {
      case CELSIUS:
        if (newUnit == TemperatureUnit.FAHRENHEIT) {
          newValue = value * 9 / 5 + 32;
        } else if (newUnit == TemperatureUnit.KELVIN) {
          newValue = value + 273.15;
        }
        break;
      case FAHRENHEIT:
        if (newUnit == TemperatureUnit.CELSIUS) {
          newValue = (value - 32) * 5 / 9;
        } else if (newUnit == TemperatureUnit.KELVIN) {
          newValue = (value - 32) * 5 / 9 + 273.15;
        }
        break;
      case KELVIN:
        if (newUnit == TemperatureUnit.CELSIUS) {
          newValue = value - 273.15;
        } else if (newUnit == TemperatureUnit.FAHRENHEIT) {
          newValue = (value - 273.15) * 9 / 5 + 32;
        }
        break;
    }
    return new Temperature(newValue, newUnit);
  }


  public String toString() {
    String stringRep = String.format("%.2f", value);
    switch (unit) {
      case CELSIUS:
        stringRep += "°C";
        break;
      case FAHRENHEIT:
        stringRep += "°F";
        break;
      case KELVIN:
        stringRep += "K";
        break;
    }
    return stringRep;
  }
}

