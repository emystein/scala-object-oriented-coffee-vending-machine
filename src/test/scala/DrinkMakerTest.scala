import org.scalatest.FunSuite

class DrinkMakerTest extends FunSuite {
  private val chocolatePrice: Double = DrinkPriceList.priceOf("Chocolate")

  test("Make drink without sugar") {
    val drink = DrinkMaker(DrinkOrder("Chocolate", 0), chocolatePrice)

    assert(drink.sugarCount == 0)
  }

  test("Make drink with 2 sugar") {
    val drink = DrinkMaker(DrinkOrder("Chocolate", 2), chocolatePrice)

    assert(drink.sugarCount == 2)
  }

  test("Make drink with no sugar should not include stick") {
    val drink = DrinkMaker(DrinkOrder("Chocolate", 0), chocolatePrice)

    assert(drink.stick.isEmpty)
  }

  test("Make drink with 1 sugar should include stick") {
    val drink = DrinkMaker(DrinkOrder("Chocolate", 1), chocolatePrice)

    assert(drink.stick.isDefined)
  }

  test("Make extra hot drink ") {
    val drink = DrinkMaker(DrinkOrder("Chocolate", 1, ExtraHotTemperature()), chocolatePrice)

    assert(drink.temperature.isInstanceOf[ExtraHotTemperature])
  }
}
