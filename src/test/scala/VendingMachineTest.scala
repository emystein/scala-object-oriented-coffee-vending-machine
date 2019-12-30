import org.scalatest.{BeforeAndAfterEach, FunSuite}

class VendingMachineTest extends FunSuite with BeforeAndAfterEach {
  private val teaPrice: Double = DrinkPriceList.priceOf("Tea")
  private val drinkMaker = new DrinkMaker()
  private var vendingMachine: VendingMachine = new VendingMachine(drinkMaker)

  override protected def beforeEach(): Unit = {
    vendingMachine = new VendingMachine(drinkMaker)
  }

  test("Set Flavor") {
    vendingMachine.setFlavor("Tea")

    assert(vendingMachine.flavor == "Tea")
  }

  test("Set sugar level") {
    vendingMachine.setSugarLevel(2)

    assert(vendingMachine.sugarLevel == 2)
  }

  test("Add money for the exact price should prepare drink") {
    vendingMachine.setFlavor("Tea")

    val result = vendingMachine.addMoney(teaPrice)

    assert(result.cup.get.drink.flavor == "Tea")
  }

  test("Add money up to exact price should prepare drink") {
    vendingMachine.setFlavor("Tea")

    val pendingAmountResult = vendingMachine.addMoney(teaPrice / 2)
    assert(pendingAmountResult.pendingAmount == teaPrice / 2)

    val result = vendingMachine.addMoney(teaPrice / 2)
    assert(result.cup.get.drink.flavor == "Tea")
  }

  test("Add money more than price should prepare drink and return change") {
    vendingMachine.setFlavor("Tea")

    val result = vendingMachine.addMoney(teaPrice * 2)

    assert(result.cup.get.drink.flavor == "Tea")
    assert(result.change == teaPrice)
  }

  test("Add money less than price should not return drink") {
    vendingMachine.setFlavor("Tea")

    val result = vendingMachine.addMoney(teaPrice / 2)

    assert(result.cup.isEmpty)
  }

  test("Add money less than price should inform pending amount") {
    vendingMachine.setFlavor("Tea")

    val result = vendingMachine.addMoney(teaPrice / 2)

    assert(result.pendingAmount == teaPrice / 2)
  }
}
