package machine.preparation

import machine.DrinkOrder

object DrinkSelection {
  def flavor(flavor: String): DrinkSelection = {
    new DrinkSelection(flavor)
  }
}

class DrinkSelection(flavor: String) {
  var sugar: Int = 0
  var temperature: Temperature = NormalTemperature

  def sugarCount(count: Int): DrinkSelection = {
    sugar = count
    this
  }

  def temperature(aTemperature: Temperature): DrinkSelection = {
    temperature = aTemperature
    this
  }

  def buildDrinkOrder: DrinkOrder = DrinkOrder(flavor, sugar, temperature)
}
