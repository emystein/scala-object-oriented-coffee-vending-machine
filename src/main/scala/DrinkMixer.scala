object DrinkMixer {
  def withFlavor(flavor: String): DrinkMixer = {
    new DrinkMixer(flavor)
  }
}

class DrinkMixer(flavor: String) {
  var sugar: Int = 0
  var temperature: Temperature = NormalTemperature()

  def sugar(count: Int): DrinkMixer = {
    sugar = count
    this
  }

  def temperature(aTemperature: Temperature): DrinkMixer = {
    temperature = aTemperature
    this
  }

  def build: Drink = Drink(flavor, sugar, temperature)
}
