package machine

import com.google.common.base.Preconditions
import machine.preparation._
import money.{AmountNotSufficient, CashRegister, Cashier, Change}

class VendingMachine(drinkMaker: DrinkMaker) {
  var cashRegister = new CashRegister
  var cashier = Cashier(cashRegister)

  var flavor: String = ""
  var sugarLevel: Int = 0
  var temperature: Temperature = NormalTemperature

  def addMoney(money: Double): PreparationResult = {
    Preconditions.checkState(!flavor.isEmpty)

    cashier.addCredit(money)

    val order = DrinkOrder(flavor, sugarLevel, temperature)

    cashier.charge(order) match {
      case Change(change) => CupAndChange(drinkMaker.prepare(order), change)
      case AmountNotSufficient(remaining) => RemainingAmount(remaining)
    }
  }
}
