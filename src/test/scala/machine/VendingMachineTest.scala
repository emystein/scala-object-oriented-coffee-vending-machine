package machine

import machine.preparation.{CupAndChange, DrinkMaker, DrinkNotSelected, ExtraHotTemperature, NormalTemperature, RemainingAmount}
import money.DrinkPriceList
import org.scalatest.{BeforeAndAfterEach, FunSuite}

class VendingMachineTest extends FunSuite with BeforeAndAfterEach {
  private val teaPrice: Double = DrinkPriceList.priceOf("Tea")
  private val drinkMaker = new DrinkMaker()
  private var vendingMachine: VendingMachine = new VendingMachine(drinkMaker)

  override protected def beforeEach(): Unit = {
    vendingMachine = new VendingMachine(drinkMaker)
  }

  test("Select all drink attributes should prepare drink") {
    vendingMachine.selectFlavor("Tea").sugarCount(1).temperature(ExtraHotTemperature)

    val result = vendingMachine.addMoney(teaPrice).asInstanceOf[CupAndChange]

    val drink = result.cup.drink
    assert(drink.flavor == "Tea")
    assert(drink.sugarCount == 1)
    assert(drink.temperature == ExtraHotTemperature)
  }

  test("Add money for the exact price should prepare drink") {
    vendingMachine.selectFlavor("Tea")

    val result = vendingMachine.addMoney(teaPrice).asInstanceOf[CupAndChange]

    assert(result.cup.drink.flavor == "Tea")
  }

  test("Add money up to exact price should prepare drink") {
    vendingMachine.selectFlavor("Tea")

    assert(vendingMachine.addMoney(teaPrice / 2).isInstanceOf[RemainingAmount])

    val result = vendingMachine.addMoney(teaPrice / 2).asInstanceOf[CupAndChange]
    assert(result.cup.drink.flavor == "Tea")
  }

  test("Add money more than price should prepare drink and return change") {
    vendingMachine.selectFlavor("Tea")

    val result = vendingMachine.addMoney(teaPrice * 2).asInstanceOf[CupAndChange]

    assert(result.cup.drink.flavor == "Tea")
    assert(result.change == teaPrice)
  }

  test("Deliver a drink should set credit to 0") {
    vendingMachine.selectFlavor("Tea")

    val result = vendingMachine.addMoney(teaPrice * 2).asInstanceOf[CupAndChange]

    assert(vendingMachine.cashRegister.credit == 0)
  }

  test("Add money less than price should inform remaining amount") {
    vendingMachine.selectFlavor("Tea")

    val remainingAmount = vendingMachine.addMoney(teaPrice / 2).asInstanceOf[RemainingAmount]

    assert(remainingAmount == teaPrice / 2)
  }

  test("Add money without setting flavor first should reject preparation") {
    assert(vendingMachine.addMoney(teaPrice) == DrinkNotSelected())
  }
}
