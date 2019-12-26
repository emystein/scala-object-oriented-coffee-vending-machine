object DrinkBuilder {
  def withFlavor(flavor: String): DrinkBuilder = {
    new DrinkBuilder(flavor)
  }
}

class DrinkBuilder(flavor: String) {
  var sugar: Int = 0
  var temperature: Temperature = NormalTemperature()

  def sugar(count: Int): DrinkBuilder = {
    sugar = count
    this
  }

  def temperature(aTemperature: Temperature): DrinkBuilder = {
    temperature = aTemperature
    this
  }

  def build: Drink = Drink(flavor, sugar, temperature)
}
