package money

import machine.DrinkOrder
import org.scalatest.{BeforeAndAfterEach, FunSuite}

class CashierTest extends FunSuite with BeforeAndAfterEach {
  val chocolatePrice = DrinkPriceList.priceOf("Chocolate")
  var cashRegister: CashRegister = null
  var cashier: Cashier = null

  override protected def beforeEach(): Unit = {
    cashRegister = new CashRegister
    cashier = Cashier(cashRegister)
  }

  test("Charge exact amount for Drink should return 0 change") {
    cashier.addCredit(chocolatePrice)

    assert(cashier.charge(DrinkOrder("Chocolate")) == Change(BigDecimal(0)))
  }

  test("Reject to make drink when incorrect amount is deposited") {
    cashier.addCredit(chocolatePrice - 0.1)

    val result = cashier.charge(DrinkOrder("Chocolate"))
    assert(result == AmountNotSufficient(BigDecimal(0.1)))
  }

  test("Charge more amount for Drink should return change") {
    cashier.addCredit(chocolatePrice + 0.1)

    assert(cashier.charge(DrinkOrder("Chocolate")) == Change(BigDecimal(0.1)))
  }

  test("Charge more amount for Drink should set CashRegister change") {
    cashier.addCredit(chocolatePrice + 0.1)

    assert(cashier.charge(DrinkOrder("Chocolate")) == Change(BigDecimal(0.1)))
  }
}
