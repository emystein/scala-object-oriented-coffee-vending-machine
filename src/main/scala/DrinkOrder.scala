case class DrinkOrder(flavor: String, sugarCount: Int, temperature: Temperature = NormalTemperature()) {
  def includeStick: Boolean = sugarCount > 0
}
