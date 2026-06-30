package edu.rit.swen352.tdd.easy;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Test suite for the {@link SimpleBankAccount} component.
 */
class SimpleBankAccountTest {

  @Test
  @DisplayName("ctor : 1 : construct with positive initial balance")
  void ctor_1() {
    final SimpleBankAccount CuT = new SimpleBankAccount(100.00f);
    assertAll("ctor with positive balance"
      , () -> assertNotNull(CuT)
      , () -> assertEquals(100.00f, CuT.getBalance(), 0.001f, "Balance is the supplied value")
    );
  }

  @Test
  @DisplayName("ctor : 2 : no-arg ctor defaults balance to zero")
  void ctor_2() {
    final SimpleBankAccount CuT = new SimpleBankAccount();
    assertAll("no-arg ctor"
      , () -> assertNotNull(CuT)
      , () -> assertEquals(0.00f, CuT.getBalance(), 0.001f, "Default balance is zero")
    );
  }

  @Test
  @DisplayName("ctor : 3 : reject negative initial balance")
  void ctor_3_fail() {
    final Exception e = assertThrows(IllegalArgumentException.class,
        () -> new SimpleBankAccount(-0.01f));
    assertEquals(SimpleBankAccount.NEGATIVE_BALANCE_ERROR, e.getMessage());
  }

  @Test
  @DisplayName("isAccountEmpty : 1 : true when balance is zero")
  void isAccountEmpty_1() {
    final SimpleBankAccount CuT = new SimpleBankAccount();
    assertTrue(CuT.isAccountEmpty(), "Empty account reports as empty");
  }

  @Test
  @DisplayName("isAccountEmpty : 2 : false when balance is positive")
  void isAccountEmpty_2() {
    final SimpleBankAccount CuT = new SimpleBankAccount(0.01f);
    assertFalse(CuT.isAccountEmpty(), "Account with funds is not empty");
  }

  @Test
  @DisplayName("deposit : 1 : positive amount increases balance")
  void deposit_1() {
    final SimpleBankAccount CuT = new SimpleBankAccount(10.00f);
    CuT.deposit(15.50f);
    assertEquals(25.50f, CuT.getBalance(), 0.001f, "Balance reflects deposited amount");
  }

  @ParameterizedTest(name = "amount={0}")
  @ValueSource(floats = {-0.01f, -1.00f, -100.00f})
  @DisplayName("deposit : 2 : reject negative amount")
  void deposit_2_fail(float amount) {
    final SimpleBankAccount CuT = new SimpleBankAccount(10.00f);
    final Exception e = assertThrows(IllegalArgumentException.class,
        () -> CuT.deposit(amount));
    assertAll("rejection leaves account untouched"
      , () -> assertEquals(SimpleBankAccount.NON_POSITIVE_AMOUNT_ERROR, e.getMessage())
      , () -> assertEquals(10.00f, CuT.getBalance(), 0.001f, "Balance unchanged")
    );
  }

  @Test
  @DisplayName("deposit : 3 : reject zero amount (requirements gap)")
  void deposit_3_fail() {
    final SimpleBankAccount CuT = new SimpleBankAccount(10.00f);
    final Exception e = assertThrows(IllegalArgumentException.class,
        () -> CuT.deposit(0.00f));
    assertAll("rejection leaves account untouched"
      , () -> assertEquals(SimpleBankAccount.NON_POSITIVE_AMOUNT_ERROR, e.getMessage())
      , () -> assertEquals(10.00f, CuT.getBalance(), 0.001f, "Balance unchanged")
    );
  }

  @Test
  @DisplayName("withdraw : 1 : valid amount decreases balance")
  void withdraw_1() {
    final SimpleBankAccount CuT = new SimpleBankAccount(50.00f);
    CuT.withdraw(20.50f);
    assertEquals(29.50f, CuT.getBalance(), 0.001f, "Balance reflects withdrawn amount");
  }

  @Test
  @DisplayName("withdraw : 2 : reject amount exceeding balance")
  void withdraw_2_fail() {
    final SimpleBankAccount CuT = new SimpleBankAccount(50.00f);
    final Exception e = assertThrows(IllegalStateException.class,
        () -> CuT.withdraw(50.01f));
    assertAll("rejection leaves account untouched"
      , () -> assertEquals(SimpleBankAccount.INSUFFICIENT_FUNDS_ERROR, e.getMessage())
      , () -> assertEquals(50.00f, CuT.getBalance(), 0.001f, "Balance unchanged")
    );
  }

  @ParameterizedTest(name = "amount={0}")
  @ValueSource(floats = {-0.01f, -1.00f, -100.00f})
  @DisplayName("withdraw : 3 : reject negative amount")
  void withdraw_3_fail(float amount) {
    final SimpleBankAccount CuT = new SimpleBankAccount(50.00f);
    final Exception e = assertThrows(IllegalArgumentException.class,
        () -> CuT.withdraw(amount));
    assertAll("rejection leaves account untouched"
      , () -> assertEquals(SimpleBankAccount.NON_POSITIVE_AMOUNT_ERROR, e.getMessage())
      , () -> assertEquals(50.00f, CuT.getBalance(), 0.001f, "Balance unchanged")
    );
  }

  @Test
  @DisplayName("withdraw : 4 : reject zero amount (requirements gap)")
  void withdraw_4_fail() {
    final SimpleBankAccount CuT = new SimpleBankAccount(50.00f);
    final Exception e = assertThrows(IllegalArgumentException.class,
        () -> CuT.withdraw(0.00f));
    assertAll("rejection leaves account untouched"
      , () -> assertEquals(SimpleBankAccount.NON_POSITIVE_AMOUNT_ERROR, e.getMessage())
      , () -> assertEquals(50.00f, CuT.getBalance(), 0.001f, "Balance unchanged")
    );
  }

  @Test
  @DisplayName("withdraw : 5 : exact balance amount empties the account (boundary)")
  void withdraw_5_boundary() {
    final SimpleBankAccount CuT = new SimpleBankAccount(50.00f);
    CuT.withdraw(50.00f);
    assertAll("account is fully drained"
      , () -> assertEquals(0.00f, CuT.getBalance(), 0.001f, "Balance is now zero")
      , () -> assertTrue(CuT.isAccountEmpty(), "Account is empty after full withdrawal")
    );
  }

  @Test
  @DisplayName("toString : 1 : format zero balance")
  void toString_1() {
    final SimpleBankAccount CuT = new SimpleBankAccount();
    assertEquals("$0.00", CuT.toString());
  }

}
