import org.scalatest.{BeforeAndAfterEach, FunSuite}

class VendingMachineTest extends FunSuite with BeforeAndAfterEach {
  private val teaPrice: Double = DrinkPriceList.priceOf("Tea")
  private val drinkMaker = new DrinkMaker()
  private var vendingMachine: VendingMachine = new VendingMachine(drinkMaker)

  override protected def beforeEach(): Unit = {
    vendingMachine = new VendingMachine(drinkMaker)
  }

  test("Initial state") {
    assert(vendingMachine.credit == 0)
    assert(vendingMachine.flavor.isEmpty)
    assert(vendingMachine.sugarLevel == 0)
    assert(vendingMachine.temperature.isInstanceOf[NormalTemperature])
  }

  test("Set Flavor") {
    vendingMachine.setFlavor("Tea")

    assert(vendingMachine.flavor == "Tea")
  }

  test("Set sugar level") {
    vendingMachine.setSugarLevel(2)

    assert(vendingMachine.sugarLevel == 2)
  }

  test("Set extra hot temperature") {
    vendingMachine.setTemperature(ExtraHotTemperature())

    assert(vendingMachine.temperature.isInstanceOf[ExtraHotTemperature])
  }

  test("Add money for the exact price should prepare drink") {
    vendingMachine.setFlavor("Tea")

    val result = vendingMachine.addMoney(teaPrice).asInstanceOf[CupAndChange]

    assert(result.cup.get.drink.flavor == "Tea")
  }

  test("Add money up to exact price should prepare drink") {
    vendingMachine.setFlavor("Tea")

    val pendingAmountResult = vendingMachine.addMoney(teaPrice / 2).asInstanceOf[PendingAmountProcessResult]
    assert(pendingAmountResult.pendingAmount == teaPrice / 2)

    val result = vendingMachine.addMoney(teaPrice / 2).asInstanceOf[CupAndChange]
    assert(result.cup.get.drink.flavor == "Tea")
  }

  test("Add money more than price should prepare drink and return change") {
    vendingMachine.setFlavor("Tea")

    val result = vendingMachine.addMoney(teaPrice * 2).asInstanceOf[CupAndChange]

    assert(result.cup.get.drink.flavor == "Tea")
    assert(result.change == teaPrice)
  }

  test("Add money less than price should inform pending amount") {
    vendingMachine.setFlavor("Tea")

    val result = vendingMachine.addMoney(teaPrice / 2).asInstanceOf[PendingAmountProcessResult]

    assert(result.pendingAmount == teaPrice / 2)
  }

  test("Add money without setting flavor first should throw IllegalStateException") {
    assertThrows[IllegalStateException](vendingMachine.addMoney(teaPrice * 2))
  }
}
