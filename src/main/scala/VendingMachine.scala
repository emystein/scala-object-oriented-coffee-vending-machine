class VendingMachine(drinkMaker: DrinkMaker) {
  var credit: Double = 0
  var flavor: String = ""
  var sugarLevel: Int = 0

  def addMoney(money: Double): VendingProcessResult = {
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

  def setSugarLevel(value: Int): Unit = sugarLevel = value
}
