object DrinkMaker {
  def apply(flavorCode: String, sugarCount: Int = 0): DrinkMaker = {
    val flavor = flavorCode match {
      case "C" => "Coffee"
      case "T" => "Tea"
      case "H" => "Chocolate"
      case _ => ""
    }

    new DrinkMaker(flavor, sugarCount)
  }
}

case class DrinkMaker(flavor: String, sugarCount: Int = 0) {
  val stick = sugarCount > 0
}
