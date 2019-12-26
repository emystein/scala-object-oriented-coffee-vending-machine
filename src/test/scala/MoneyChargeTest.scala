import org.scalatest.FunSuite

import org.scalatest.Matchers._

class MoneyChargeTest extends FunSuite {
  test("Make Coffee when correct amount is deposited") {
    val drink = DrinkMaker("Coffee", 1, 0.6)

    assert(drink != null)
  }

  test("Make Tea when correct amount is deposited") {
    val drink = DrinkMaker("Tea", 1, 0.4)

    assert(drink != null)
  }

  test("Make Chocolate when correct amount is deposited") {
    val drink = DrinkMaker("Tea", 1, 0.5)

    assert(drink != null)
  }

  test("Make Orange Juice when correct amount is deposited") {
    val drink = DrinkMaker("Orange Juice", 0, 0.6)

    assert(drink != null)
  }

  test("Make drink when bigger amount is deposited") {
    val drink = DrinkMaker("Coffee", 1, 0.7)

    assert(drink != null)
  }

  test("Reject to make drink when incorrect amount is deposited") {
    the[AmountNotSufficientException] thrownBy DrinkMaker("Coffee", 1, 0.4) should have message "Amount not sufficient: 0.4"
  }
}
