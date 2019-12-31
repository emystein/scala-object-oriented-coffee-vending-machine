import com.google.common.base.Preconditions

class VendingMachine(drinkMaker: DrinkMaker) {
  var credit: Double = 0
  var flavor: String = ""
  var sugarLevel: Int = 0
  var temperature: Temperature = NormalTemperature()

  def addMoney(money: Double): VendingProcessResult = {
    Preconditions.checkState(!flavor.isEmpty)

    credit += money

    val priceList = DrinkPriceList.priceOf(flavor)

    try {
      val change = Cashier.charge(flavor, credit)
      val cup = drinkMaker.prepare(DrinkOrder(flavor, sugarLevel), credit)
      CupAndChange(Some(cup), change)
    } catch {
      case AmountNotSufficientException(amountGiven) => PendingAmountProcessResult(priceList - amountGiven)
    }
  }

  def setFlavor(aFlavor: String): Unit = flavor = aFlavor

  def setSugarLevel(aNumber: Int): Unit = sugarLevel = aNumber

  def setTemperature(aTemperature: Temperature) = temperature = aTemperature
}
