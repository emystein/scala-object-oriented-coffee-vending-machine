package money

import org.scalatest.FunSuite
import org.scalatest.Matchers._

class CashierTest extends FunSuite {
  val chocolatePrice = DrinkPriceList.priceOf("Chocolate")

  test("Charge exact amount for machine.Drink should return 0 change") {
    assert(Cashier.charge("Chocolate", chocolatePrice) == BigDecimal(0))
  }

  test("Reject to make drink when incorrect amount is deposited") {
    the[AmountNotSufficientException] thrownBy
      Cashier.charge("Chocolate", chocolatePrice - 0.1) should have message "Amount not sufficient: 0.4"
  }

  test("Charge more amount for machine.Drink should return change") {
    assert(Cashier.charge("Chocolate", chocolatePrice + 0.1) == BigDecimal(0.1))
  }
}
