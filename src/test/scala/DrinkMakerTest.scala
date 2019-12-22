import org.scalatest.FunSuite

class DrinkMakerTest extends FunSuite {
  test("Make Coffee") {
    val drink = DrinkMaker("C", 0, 0.6)

    assert(drink.flavor == "Coffee")
  }

  test("Make Tea") {
    val drink = DrinkMaker("T", 0, 0.4)

    assert(drink.flavor == "Tea")
  }

  test("Make Chocolate") {
    val drink = DrinkMaker("H", 0, 0.5)

    assert(drink.flavor == "Chocolate")
  }

  test("Make Orange Juice") {
    val drink = DrinkMaker("O", 0, 0.6)

    assert(drink.flavor == "Orange Juice")
  }

  test("Make drink without sugar") {
    val drink = DrinkMaker("H", 0, 0.5)

    assert(drink.sugarCount == 0)
  }

  test("Make drink with 1 sugar") {
    val drink = DrinkMaker("H", 1, 0.5)

    assert(drink.sugarCount == 1)
  }

  test("Make drink with 2 sugar") {
    val drink = DrinkMaker("H", 2, 0.5)

    assert(drink.sugarCount == 2)
  }

  test("Make drink with no sugar should not include stick") {
    val drink = DrinkMaker("H", 0, 0.5)

    assert(!drink.includeStick)
  }

  test("Make drink with 1 sugar should include stick") {
    val drink = DrinkMaker("H", 1, 0.5)

    assert(drink.flavor == "Chocolate")
    assert(drink.sugarCount == 1)
    assert(drink.includeStick)
  }

  test("Make drink extra hot") {
    val drink = DrinkMaker("H", 1, 0.5, true)

    assert(drink.extraHot)
  }

}
