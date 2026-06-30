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
    throw new UnsupportedOperationException("NYI");
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
