import com.google.common.base.Preconditions

class VendingMachine(drinkMaker: DrinkMaker) {
  var credit: Double = 0
  var flavor: String = ""
  var sugarLevel: Int = 0
  var temperature: Temperature = NormalTemperature()

  def addMoney(money: Double): VendingProcessResult = {
    Preconditions.checkState(!flavor.isEmpty)

    credit += money

    try {
      val change = Cashier.charge(flavor, credit)
      val cup = drinkMaker.prepare(DrinkOrder(flavor, sugarLevel), credit)
      credit = 0
      CupAndChange(Some(cup), change)
    } catch {
      case AmountNotSufficientException(amountGiven) => PendingAmountProcessResult(DrinkPriceList.priceOf(flavor) - amountGiven)
    }
  }

  def setFlavor(aFlavor: String): Unit = flavor = aFlavor

  def setSugarLevel(aNumber: Int): Unit = sugarLevel = aNumber

  def setTemperature(aTemperature: Temperature): Unit = temperature = aTemperature
}
