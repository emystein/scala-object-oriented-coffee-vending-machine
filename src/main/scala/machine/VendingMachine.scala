package machine

import com.google.common.base.Preconditions
import money.{AmountNotSufficientException, Cashier, DrinkPriceList}

class VendingMachine(drinkMaker: DrinkMaker) {
  var flavor: String = ""
  var sugarLevel: Int = 0
  var temperature: Temperature = NormalTemperature()

  var credit: Double = 0

  def addMoney(money: Double): PreparationResult = {
    Preconditions.checkState(!flavor.isEmpty)

    credit += money

    try {
      val change = Cashier.charge(flavor, credit)
      credit = 0
      val cup = drinkMaker.prepare(DrinkOrder(flavor, sugarLevel))
      CupAndChange(Some(cup), change)
    } catch {
      case AmountNotSufficientException(amountGiven) => RemainingAmount(DrinkPriceList.priceOf(flavor) - amountGiven)
    }
  }
}
