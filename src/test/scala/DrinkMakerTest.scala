import org.scalatest.FunSuite

class DrinkMakerTest extends FunSuite {
  test("Make Coffee") {
    val drink = DrinkMaker("C")

    assert(drink.flavor == "Coffee")
  }

  test("Make Tea") {
    val drink = DrinkMaker("T")

    assert(drink.flavor == "Tea")
  }

  test("Make Chocolate") {
    val drink = DrinkMaker("H")

    assert(drink.flavor == "Chocolate")
  }
    
  test("Make drink without sugar") {
    val drink = DrinkMaker("H")

    assert(drink.sugarCount == 0)
  }

  test("Make drink with 1 sugar") {
    val drink = DrinkMaker("H", 1)

    assert(drink.sugarCount == 1)
  }

  test("Make drink with 2 sugar") {
    val drink = DrinkMaker("H", 2)

    assert(drink.sugarCount == 2)
  }

  test("Make drink with no sugar should not include stick") {
    val drink = DrinkMaker("H")
    assert(!drink.stick)
  }

  test("Make drink with 1 sugar should include stick") {
    val drink = DrinkMaker("H", 1)

    assert(drink.flavor == "Chocolate")
    assert(drink.sugarCount == 1)
    assert(drink.stick)
  }
}
