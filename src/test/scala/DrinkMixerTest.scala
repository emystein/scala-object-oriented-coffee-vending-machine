import org.scalatest.FunSuite

class DrinkMixerTest extends FunSuite {
  test("Build drink with no sugar and normal temperature") {
    val drink = DrinkMixer.withFlavor("Tea").build

    assert(drink.flavor == "Tea")
    assert(drink.temperature.isInstanceOf[NormalTemperature])
  }

  test("Build drink with 1 sugar") {
    val drink = DrinkMixer.withFlavor("Tea").sugar(1).build

    assert(drink.sugarCount == 1)
  }

  test("Build extra hot drink") {
    val drink = DrinkMixer.withFlavor("Tea").temperature(ExtraHotTemperature()).build

    assert(drink.temperature.isInstanceOf[ExtraHotTemperature])
  }

  test("Build extra hot drink with 2 sugars") {
    val drink = DrinkMixer.withFlavor("Tea").sugar(2).temperature(ExtraHotTemperature()).build

    assert(drink.sugarCount == 2)
    assert(drink.temperature.isInstanceOf[ExtraHotTemperature])
  }
}
