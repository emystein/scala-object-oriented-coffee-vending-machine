package machine

import org.scalatest.FunSuite

class DrinkMixerTest extends FunSuite {
  test("Mix drink with no sugar and normal temperature") {
    val drink = DrinkMixer.flavor("Tea").build

    assert(drink.flavor == "Tea")
    assert(drink.temperature.isInstanceOf[NormalTemperature])
  }

  test("Mix drink with 1 sugar") {
    val drink = DrinkMixer.flavor("Tea").sugarCount(1).build

    assert(drink.sugarCount == 1)
  }

  test("Mix extra hot drink") {
    val drink = DrinkMixer.flavor("Tea").temperature(ExtraHotTemperature()).build

    assert(drink.temperature.isInstanceOf[ExtraHotTemperature])
  }

  test("Mix extra hot drink with 2 sugars") {
    val drink = DrinkMixer.flavor("Tea").sugarCount(2).temperature(ExtraHotTemperature()).build

    assert(drink.sugarCount == 2)
    assert(drink.temperature.isInstanceOf[ExtraHotTemperature])
  }
}
