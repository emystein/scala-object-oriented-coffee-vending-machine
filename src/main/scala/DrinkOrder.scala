trait DrinkOrder {
  val flavor: String
  val sugarCount: Int
  def includeStick: Boolean = sugarCount > 0
}

case class NormalTemperatureDrinkOrder(flavor: String, sugarCount: Int) extends DrinkOrder

case class ExtraHotDrinkOrder(val flavor: String, val sugarCount: Int) extends DrinkOrder

