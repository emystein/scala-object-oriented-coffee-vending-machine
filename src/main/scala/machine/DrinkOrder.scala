package machine

import machine.preparation.{NormalTemperature, Temperature}

case class DrinkOrder(flavor: String, sugarCount: Int = 0, temperature: Temperature = NormalTemperature())
