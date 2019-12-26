import org.scalatest.FunSuite

class DrinkBuilderTest extends FunSuite {
  test("Build drink with no sugar and normal temperature") {
    val drink = DrinkBuilder.withFlavor("Tea").build

    assert(drink.flavor == "Tea")
    assert(drink.temperature.isInstanceOf[NormalTemperature])
  }

  test("Build drink with 1 sugar") {
    val drink = DrinkBuilder.withFlavor("Tea").sugar(1).build

    assert(drink.sugarCount == 1)
  }

  test("Build extra hot drink") {
    val drink = DrinkBuilder.withFlavor("Tea").temperature(ExtraHotTemperature()).build

    assert(drink.temperature.isInstanceOf[ExtraHotTemperature])
  }

  test("Build extra hot drink with 2 sugars") {
    val drink = DrinkBuilder.withFlavor("Tea").sugar(2).temperature(ExtraHotTemperature()).build

    assert(drink.sugarCount == 2)
    assert(drink.temperature.isInstanceOf[ExtraHotTemperature])
  }
}
