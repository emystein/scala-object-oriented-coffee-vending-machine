package machine

import machine.preparation.{ExtraHotTemperature, NormalTemperature}

class ProtocolParser() {
  val drinkFlavorByCode = Map('T' -> "Tea", 'C' -> "Coffee", 'H' -> "Chocolate", 'O' -> "Orange Juice")

  def parse(value: String): DrinkOrder = {
    val splitCommand = value.split(":")

    val flavor = drinkFlavorByCode(splitCommand(0).head)
    val sugarCount = if (splitCommand.size > 1) splitCommand(1).toInt else 0
    val temperature = parseTemperature(splitCommand(0))

    DrinkOrder(flavor, sugarCount, temperature)
  }

  private def parseTemperature(command: String) = {
    // need to add group to pattern (enclose .h regex with parenthesis)
    // so it can be matched with extraHotPattern(_)
    val extraHotPattern = "(.h)".r

    command match {
      case extraHotPattern(_) => ExtraHotTemperature
      case _ => NormalTemperature
    }
  }
}
