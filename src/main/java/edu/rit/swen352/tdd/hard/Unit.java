package edu.rit.swen352.tdd.hard;

/**
 * Helper Value Object for {@link Measurement}.  A Unit is a
 * dimension vector over (length, time, mass) plus a scaling
 * factor to SI base units (meters, seconds, kilograms) and a
 * human-friendly symbol.
 *
 * <p>Examples:
 * <ul>
 *   <li>meter -> (1,0,0, 1.0, "m")</li>
 *   <li>foot -> (1,0,0, 0.3048, "ft")</li>
 *   <li>mph -> (1,-1,0, 0.44704, "mi/hr")</li>
 *   <li>dimensionless -> (0,0,0, 1.0, "")</li>
 * </ul>
 */
public class Unit {

  public static final Unit METER = new Unit(1, 0, 0, 1.0, "m");
  public static final Unit SECOND = new Unit(0, 1, 0, 1.0, "s");
  public static final Unit KILOGRAM = new Unit(0, 0, 1, 1.0, "kg");
  public static final Unit DIMENSIONLESS = new Unit(0, 0, 0, 1.0, "");

  public static final Unit INCH = new Unit(1, 0, 0, 0.0254, "in");
  public static final Unit FOOT = new Unit(1, 0, 0, 0.3048, "ft");
  public static final Unit MILE = new Unit(1, 0, 0, 1609.344, "mi");
  public static final Unit KILOMETER = new Unit(1, 0, 0, 1000.0, "km");
  public static final Unit CENTIMETER = new Unit(1, 0, 0, 0.01, "cm");
  public static final Unit MINUTE = new Unit(0, 1, 0, 60.0, "min");
  public static final Unit HOUR = new Unit(0, 1, 0, 3600.0, "hr");
  public static final Unit POUND = new Unit(0, 0, 1, 0.45359237, "lb");

  private final int lengthExp;
  private final int timeExp;
  private final int massExp;
  private final double scaleToBase;
  private final String symbol;

  public Unit(int lengthExp, int timeExp, int massExp, double scaleToBase, String symbol) {
    this.lengthExp = lengthExp;
    this.timeExp = timeExp;
    this.massExp = massExp;
    this.scaleToBase = scaleToBase;
    this.symbol = symbol;
  }

  public int getLengthExp() {
    return lengthExp;
  }

  public int getTimeExp() {
    return timeExp;
  }

  public int getMassExp() {
    return massExp;
  }

  public double getScaleToBase() {
    return scaleToBase;
  }

  public String getSymbol() {
    return symbol;
  }

  public boolean isCompatible(Unit other) {
    return lengthExp == other.lengthExp
        && timeExp == other.timeExp
        && massExp == other.massExp;
  }

  public Unit multiply(Unit other) {
    return new Unit(
        lengthExp + other.lengthExp,
        timeExp + other.timeExp,
        massExp + other.massExp,
        scaleToBase * other.scaleToBase,
        combineSymbols(symbol, other.symbol, "*"));
  }

  public Unit divide(Unit other) {
    return new Unit(
        lengthExp - other.lengthExp,
        timeExp - other.timeExp,
        massExp - other.massExp,
        scaleToBase / other.scaleToBase,
        combineSymbols(symbol, other.symbol, "/"));
  }

  private static String combineSymbols(String left, String right, String op) {
    if (left.isEmpty()) return right;
    if (right.isEmpty()) return left;
    return left + op + right;
  }

  @Override
  public boolean equals(Object other) {
    if (this == other) return true;
    if (!(other instanceof Unit u)) return false;
    return lengthExp == u.lengthExp
        && timeExp == u.timeExp
        && massExp == u.massExp
        && Double.compare(scaleToBase, u.scaleToBase) == 0;
  }

  @Override
  public int hashCode() {
    int result = Integer.hashCode(lengthExp);
    result = 31 * result + Integer.hashCode(timeExp);
    result = 31 * result + Integer.hashCode(massExp);
    result = 31 * result + Double.hashCode(scaleToBase);
    return result;
  }

}
