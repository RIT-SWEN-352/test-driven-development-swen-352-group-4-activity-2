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

  public Unit(int lengthExp, int timeExp, int massExp, double scaleToBase, String symbol) {
    throw new UnsupportedOperationException("NYI");
  }

  public int getLengthExp() {
    throw new UnsupportedOperationException("NYI");
  }

  public int getTimeExp() {
    throw new UnsupportedOperationException("NYI");
  }

  public int getMassExp() {
    throw new UnsupportedOperationException("NYI");
  }

  public double getScaleToBase() {
    throw new UnsupportedOperationException("NYI");
  }

  public String getSymbol() {
    throw new UnsupportedOperationException("NYI");
  }

}
