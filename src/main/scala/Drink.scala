object DrinkMaker {
  def apply(flavorCode: String, sugarCount: Int = 0, amountPaid: Double, extraHot: Boolean = false)(implicit drinkMakeObservers: List[DrinkMakeObserver] = List()): Drink = {
    val drink = flavorCode match {
      case "T" => Drink("Tea", sugarCount, extraHot)
      case "C" => Drink("Coffee", sugarCount, extraHot)
      case "H" => Drink("Chocolate", sugarCount, extraHot)
      case "O" => Drink("Orange Juice", sugarCount, extraHot)
    }

    val listPrice = DrinkPriceList.priceOf(drink.flavor)

    if (amountPaid < listPrice)
     throw AmountNotSufficientException(amountPaid)

    drinkMakeObservers.foreach(_(drink, listPrice))

    drink
  }
}

case class Drink(flavor: String, sugarCount: Int, extraHot: Boolean) {
  val includeStick: Boolean = sugarCount > 0
}

