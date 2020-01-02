package money

import org.scalatest.FunSuite

class CashRegisterTest extends FunSuite {
  test("Given an empty Cash Register when add credit then the Cash Register should record the credit") {
    val cashRegister = new CashRegister

    cashRegister.addCredit(10)

    assert(cashRegister.credit == 10)
  }

  test("Given a Cash Register with $10 in credit when charge $8 then the Cash Register should give $2 in change") {
    val cashRegister = new CashRegister

    cashRegister.addCredit(10)

    val change = cashRegister.charge(8)

    assert(change == 2)
  }

  test("Given a Cash Register with $10 in credit when charge $8 then the Cash Register should reset credit") {
    val cashRegister = new CashRegister

    cashRegister.addCredit(10)

    cashRegister.charge(8)

    assert(cashRegister.credit == 0)
  }
}
