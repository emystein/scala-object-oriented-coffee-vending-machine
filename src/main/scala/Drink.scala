object DrinkMaker {
  def apply(flavorCode: String, sugarCount: Int = 0, amountGiven: Double): Drink = {
    val drink = flavorCode match {
      case "T" => Drink("Tea", sugarCount)
      case "C" => Drink("Coffee", sugarCount)
      case "H" => Drink("Chocolate", sugarCount)
      case "O" => Drink("Orange Juice", sugarCount)
    }

    val listPrice = DrinkPriceList.priceOf(drink.flavor)

    if (amountGiven < listPrice)
     throw AmountNotSufficientException(amountGiven)

    drink
  }
}

case class Drink(flavor: String, sugarCount: Int) {
  val includeStick: Boolean = sugarCount > 0
}

