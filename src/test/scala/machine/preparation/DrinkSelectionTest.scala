package machine.preparation

import org.scalatest.FunSuite

class DrinkSelectionTest extends FunSuite {
  test("Select drink with no sugar and normal temperature") {
    val order = DrinkSelection.flavor("Tea").buildDrinkOrder

    assert(order.flavor == "Tea")
    assert(order.temperature == NormalTemperature)
  }

  test("Select drink with 1 sugar") {
    val order = DrinkSelection.flavor("Tea").sugarCount(1).buildDrinkOrder

    assert(order.sugarCount == 1)
  }

  test("Select extra hot drink") {
    val order = DrinkSelection.flavor("Tea").temperature(ExtraHotTemperature).buildDrinkOrder

    assert(order.temperature == ExtraHotTemperature)
  }

  test("Select extra hot drink with 2 sugars") {
    val order = DrinkSelection.flavor("Tea").sugarCount(2).temperature(ExtraHotTemperature).buildDrinkOrder

    assert(order.sugarCount == 2)
    assert(order.temperature == ExtraHotTemperature)
  }
}
