package machine

import machine.preparation._
import money.{AmountNotSufficient, CashRegister, Cashier, Change}

class VendingMachine(drinkMaker: DrinkMaker) {
  var cashRegister = new CashRegister
  var cashier = Cashier(cashRegister)

  private var drinkSelection: Option[DrinkSelection] = None

  def selectFlavor(flavor: String): DrinkSelection = {
    drinkSelection = Some(DrinkSelection.flavor(flavor))
    drinkSelection.get
  }

  def addMoney(money: Double): PreparationResult = {
    cashier.addCredit(money)

    drinkSelection match {
      case None => DrinkNotSelected()
      case Some(selection) => prepare(selection)
    }
  }

  private def prepare(selection: DrinkSelection) = {
    val order = selection.buildDrinkOrder

    cashier.charge(order) match {
      case Change(change) => CupAndChange(drinkMaker.prepare(order), change)
      case AmountNotSufficient(remaining) => RemainingAmount(remaining)
    }
  }
}
