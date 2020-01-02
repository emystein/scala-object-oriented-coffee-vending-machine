package machine

import com.google.common.base.Preconditions
import money.{AmountNotSufficientException, CashRegister, Cashier, DrinkPriceList}

class VendingMachine(drinkMaker: DrinkMaker) {
  var cashier = Cashier(new CashRegister)

  var flavor: String = ""
  var sugarLevel: Int = 0
  var temperature: Temperature = NormalTemperature()

  def addMoney(money: Double): PreparationResult = {
    Preconditions.checkState(!flavor.isEmpty)

    cashier.addCredit(money)

    try {
      val change = cashier.charge(flavor)
      val cup = drinkMaker.prepare(DrinkOrder(flavor, sugarLevel))
      CupAndChange(Some(cup), change)
    } catch {
      case AmountNotSufficientException(amountGiven) => RemainingAmount(DrinkPriceList.priceOf(flavor) - amountGiven)
    }
  }
}
