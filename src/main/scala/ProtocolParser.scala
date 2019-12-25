class ProtocolParser() {
  val drinkFlavorByCode = Map('T' -> "Tea", 'C' -> "Coffee", 'H' -> "Chocolate", 'O' -> "Orange Juice")

  def parse(value: String): DrinkOrder = {
    val splitCommand = value.split(":")

    val sugarCount = if (splitCommand.size > 1) splitCommand(1).toInt else 0

    val flavor = drinkFlavorByCode(splitCommand(0).head)

    // need to add group to pattern (enclose .h regex with parenthesis)
    // so it can be matched with extraHotPattern(_)
    val extraHotPattern = "(.h)".r

    splitCommand(0) match {
      case extraHotPattern(_) => ExtraHotDrinkOrder(flavor, sugarCount)
      case _ => NormalTemperatureDrinkOrder(flavor, sugarCount)
    }
  }
}
