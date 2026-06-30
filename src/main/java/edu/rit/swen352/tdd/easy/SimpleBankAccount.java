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

  static final String NEGATIVE_BALANCE_ERROR = "balance must not be negative";
  static final String NON_POSITIVE_AMOUNT_ERROR = "amount must be positive";

  private float balance;

  public SimpleBankAccount(float initialBalance) {
    if (initialBalance < 0.0f) {
      throw new IllegalArgumentException(NEGATIVE_BALANCE_ERROR);
    }
    this.balance = initialBalance;
  }

  public SimpleBankAccount() {
    this(0.0f);
  }

  public float getBalance() {
    return balance;
  }

  public boolean isAccountEmpty() {
    return balance == 0.0f;
  }

  public void deposit(float amount) {
    balance += amount;
  }

}
