import org.scalatest.FunSuite

class DrinkMakerTest extends FunSuite {
  private val chocolatePrice: Double = DrinkPriceList.priceOf("Chocolate")
  private val drinkMaker = new DrinkMaker()

  test("Make drink without sugar") {
    val cup = drinkMaker.prepare(DrinkOrder("Chocolate", 0), chocolatePrice)

    assert(cup.drink.sugarCount == 0)
  }

  test("Make drink with 2 sugar") {
    val cup = drinkMaker.prepare(DrinkOrder("Chocolate", 2), chocolatePrice)

    assert(cup.drink.sugarCount == 2)
  }

  test("Make drink with no sugar should not include stick") {
    val cup = drinkMaker.prepare(DrinkOrder("Chocolate", 0), chocolatePrice)

    assert(cup.stick.isEmpty)
  }

  test("Make drink with 1 sugar should include stick") {
    val cup = drinkMaker.prepare(DrinkOrder("Chocolate", 1), chocolatePrice)

    assert(cup.stick.isDefined)
  }

  test("Make extra hot drink ") {
    val cup = drinkMaker.prepare(DrinkOrder("Chocolate", 1, ExtraHotTemperature()), chocolatePrice)

    assert(cup.drink.temperature.isInstanceOf[ExtraHotTemperature])
  }
}
