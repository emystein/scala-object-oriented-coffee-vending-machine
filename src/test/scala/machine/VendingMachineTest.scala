package machine

import money.DrinkPriceList
import org.scalatest.{BeforeAndAfterEach, FunSuite}

class VendingMachineTest extends FunSuite with BeforeAndAfterEach {
  private val teaPrice: Double = DrinkPriceList.priceOf("Tea")
  private val drinkMaker = new DrinkMaker()
  private var vendingMachine: VendingMachine = new VendingMachine(drinkMaker)

  override protected def beforeEach(): Unit = {
    vendingMachine = new VendingMachine(drinkMaker)
  }

  test("Initial state") {
    assert(vendingMachine.flavor.isEmpty)
    assert(vendingMachine.sugarLevel == 0)
    assert(vendingMachine.temperature.isInstanceOf[NormalTemperature])
  }

  test("Add money for the exact price should prepare drink") {
    vendingMachine.flavor = "Tea"

    val result = vendingMachine.addMoney(teaPrice).asInstanceOf[CupAndChange]

    assert(result.cup.get.drink.flavor == "Tea")
  }

  test("Add money up to exact price should prepare drink") {
    vendingMachine.flavor = "Tea"

    val pendingAmountResult = vendingMachine.addMoney(teaPrice / 2).asInstanceOf[RemainingAmount]
    assert(pendingAmountResult.remainingAmount == teaPrice / 2)

    val result = vendingMachine.addMoney(teaPrice / 2).asInstanceOf[CupAndChange]
    assert(result.cup.get.drink.flavor == "Tea")
  }

  test("Add money more than price should prepare drink and return change") {
    vendingMachine.flavor = "Tea"

    val result = vendingMachine.addMoney(teaPrice * 2).asInstanceOf[CupAndChange]

    assert(result.cup.get.drink.flavor == "Tea")
    assert(result.change == teaPrice)
  }

  test("Deliver a drink should set credit to 0") {
    vendingMachine.flavor = "Tea"

    val result = vendingMachine.addMoney(teaPrice * 2).asInstanceOf[CupAndChange]

    assert(vendingMachine.cashier.cashRegister.credit == 0)
  }

  test("Add money less than price should inform pending amount") {
    vendingMachine.flavor = "Tea"

    val result = vendingMachine.addMoney(teaPrice / 2).asInstanceOf[RemainingAmount]

    assert(result.remainingAmount == teaPrice / 2)
  }

  test("Add money without setting flavor first should throw IllegalStateException") {
    assertThrows[IllegalStateException](vendingMachine.addMoney(teaPrice * 2))
  }
}
