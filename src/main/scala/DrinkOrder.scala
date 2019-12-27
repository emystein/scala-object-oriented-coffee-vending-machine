case class DrinkOrder(flavor: String, sugarCount: Int = 0, temperature: Temperature = NormalTemperature()) {
  def includeStick: Boolean = sugarCount > 0
}
