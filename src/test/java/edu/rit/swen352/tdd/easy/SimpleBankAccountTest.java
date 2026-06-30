package edu.rit.swen352.tdd.easy;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

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

}
