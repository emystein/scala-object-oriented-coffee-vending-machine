package machine

import com.google.common.base.Preconditions
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

    try {
      val order = DrinkOrder(flavor, sugarLevel)
      val change = cashier.charge(order)
      val cup = drinkMaker.prepare(order)
      CupAndChange(Some(cup), change)
    } catch {
      case AmountNotSufficientException(amountRemaining) => RemainingAmount(amountRemaining)
    }
  }
}
