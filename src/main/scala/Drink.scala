object DrinkMaker {
  def apply(flavor: String, sugarCount: Int = 0, amountPaid: Double, temperature: Temperature = NormalTemperature())
           (implicit drinkMakePreconditions: List[DrinkMakePrecondition] = List(),
            drinkMakeObservers: List[DrinkMakeObserver] = List()): Drink = {

    drinkMakePreconditions.foreach(_ (flavor))

    val drink = DrinkBuilder
      .withFlavor(flavor)
      .sugar(sugarCount)
      .temperature(temperature)
      .build

    Cashier.charge(drink.flavor, amountPaid)

    drinkMakeObservers.foreach(_ (drink))

    drink
  }
}

case class Drink(flavor: String, sugarCount: Int, temperature: Temperature) {
  val includeStick: Boolean = sugarCount > 0
}

