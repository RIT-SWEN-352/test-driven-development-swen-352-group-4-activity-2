package edu.rit.swen352.tdd.easy;

/**
 * An American bank account that permits deposits and withdrawals.
 * The balance must never be negative.  The balance must be stored
 * as a {@code float}.
 *
 * <p>
 * You must implement these features:
 * <ul>
 *   <li>constructor:
 *     <ul>
 *       <li>a ctor that supplies an initial balance, as a {@code float} value</li>
 *       <li>a no-arg ctor that defaults the initial balance to zero</li>
 *     </ul>
 *   </li>
 *   <li>{@code getBalance()}: returns the current balance</li>
 *   <li>{@code isAccountEmpty()}: queries whether the balance is zero</li>
 *   <li>{@code deposit(amount)}: add a {@code float} amount to the balance</li>
 *   <li>{@code withdraw(amount)}: subtract a {@code float} amount from the balance</li>
 *   <li>{@code toString()}: returns a human-friendly representation of the account balance, eg {@code $20.50}</li>
 * </ul>
 */
public class SimpleBankAccount {

  private float balance;

  public SimpleBankAccount(float initialBalance) {
    this.balance = initialBalance;
  }

  public float getBalance() {
    return balance;
  }

}
