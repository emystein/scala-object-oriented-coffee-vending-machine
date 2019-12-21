import org.scalatest.FunSuite

import org.scalatest.Matchers._

class MoneyChargeTest extends FunSuite {
  test("Make drink when correct amount is deposited") {
    val drink = DrinkMaker("C", 1, 0.6)

    assert(drink != null)
  }

  test("Make drink when bigger amount is deposited") {
    val drink = DrinkMaker("C", 1, 0.7)

    assert(drink != null)
  }

  test("Reject to make drink when incorrect amount is deposited") {
    the[AmountNotSufficientException] thrownBy DrinkMaker("C", 1, 0.4) should have message "Amount not sufficient: 0.4"
  }
}
