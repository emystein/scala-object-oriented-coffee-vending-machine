object DrinkMaker {
  def apply(flavorCode: String, sugarCount: Int = 0, amountGiven: Double, extraHot: Boolean = false): Drink = {
    val drink = flavorCode match {
      case "T" => Drink("Tea", sugarCount, extraHot)
      case "C" => Drink("Coffee", sugarCount, extraHot)
      case "H" => Drink("Chocolate", sugarCount, extraHot)
      case "O" => Drink("Orange Juice", sugarCount, extraHot)
    }

    val listPrice = DrinkPriceList.priceOf(drink.flavor)

    if (amountGiven < listPrice)
     throw AmountNotSufficientException(amountGiven)

    drink
  }
}

case class Drink(flavor: String, sugarCount: Int, extraHot: Boolean) {
  val includeStick: Boolean = sugarCount > 0
}

