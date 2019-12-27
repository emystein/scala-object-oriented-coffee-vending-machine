import org.scalatest.FunSuite

class DrinkPriceListTest extends FunSuite {
  test("Charge 0.4 euro for Tea") {
    assert(DrinkPriceList.priceOf("Tea") == 0.4)
  }

  test("Charge 0.6 euro for Coffee") {
    assert(DrinkPriceList.priceOf("Coffee") == 0.6)
  }

  test("Charge 0.5 euro for Chocolate") {
    assert(DrinkPriceList.priceOf("Chocolate") == 0.5)
  }

  test("Charge 0.6 euro for Orange Juice") {
    assert(DrinkPriceList.priceOf("Orange Juice") == 0.6)
  }
}
