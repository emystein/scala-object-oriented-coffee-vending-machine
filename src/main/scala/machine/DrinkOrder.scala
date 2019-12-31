package machine

case class DrinkOrder(flavor: String, sugarCount: Int = 0, temperature: Temperature = NormalTemperature())
