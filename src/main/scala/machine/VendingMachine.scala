package machine

import com.google.common.base.Preconditions
import machine.preparation.{CupAndChange, DrinkMaker, NormalTemperature, PreparationResult, RemainingAmount, Temperature}
import money.{AmountNotSufficientException, CashRegister, Cashier}

class VendingMachine(drinkMaker: DrinkMaker) {
  var cashRegister = new CashRegister
  var cashier = Cashier(cashRegister)

  var flavor: String = ""
  var sugarLevel: Int = 0
  var temperature: Temperature = NormalTemperature()

  def addMoney(money: Double): PreparationResult = {
    Preconditions.checkState(!flavor.isEmpty)

    cashier.addCredit(money)

    val order = DrinkOrder(flavor, sugarLevel)

    try {
      val change = cashier.charge(order)
      val cup = drinkMaker.prepare(order)
      CupAndChange(cup, change)
    } catch {
      case AmountNotSufficientException(amountRemaining) => RemainingAmount(amountRemaining)
    }
  }
}
