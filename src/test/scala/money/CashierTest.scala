package money

import org.scalatest.{BeforeAndAfterEach, FunSuite}
import org.scalatest.Matchers._

class CashierTest extends FunSuite with BeforeAndAfterEach {
  val chocolatePrice = DrinkPriceList.priceOf("Chocolate")
  var cashRegister: CashRegister = null
  var cashier: Cashier = null

  override protected def beforeEach(): Unit = {
    cashRegister = new CashRegister
    cashier = new Cashier(cashRegister)
  }

  test("Charge exact amount for Drink should return 0 change") {
    cashier.addCredit(chocolatePrice)

    assert(cashier.charge("Chocolate", chocolatePrice) == BigDecimal(0))
  }

  test("Reject to make drink when incorrect amount is deposited") {
    cashier.addCredit(chocolatePrice - 0.1)

    the[AmountNotSufficientException] thrownBy
      cashier.charge("Chocolate", chocolatePrice - 0.1) should have message "Amount not sufficient: 0.4"
  }

  test("Charge more amount for Drink should return change") {
    cashier.addCredit(chocolatePrice + 0.1)

    assert(cashier.charge("Chocolate", chocolatePrice + 0.1) == BigDecimal(0.1))
  }

  test("Charge more amount for Drink should set CashRegister change") {
    cashier.addCredit(chocolatePrice + 0.1)

    assert(cashier.charge("Chocolate", chocolatePrice + 0.1) == BigDecimal(0.1))
    assert(cashRegister.change == BigDecimal(0.1))
  }
}
