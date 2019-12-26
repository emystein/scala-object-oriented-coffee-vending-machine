object DrinkMaker {
  def apply(order: DrinkOrder, amountPaid: Double)
           (implicit drinkMakePreconditions: List[DrinkMakePrecondition] = List(),
            drinkMakeObservers: List[DrinkMakeObserver] = List()): Drink = {

    drinkMakePreconditions.foreach(_ (order.flavor))

    val drink = DrinkBuilder
      .withFlavor(order.flavor)
      .sugar(order.sugarCount)
      .temperature(order.temperature)
      .build

    Cashier.charge(drink.flavor, amountPaid)

    drinkMakeObservers.foreach(_ (drink))

    drink
  }
}

case class Drink(flavor: String, sugarCount: Int, temperature: Temperature) {
  val includeStick: Boolean = sugarCount > 0
}
