object DrinkMaker {
  def apply(order: DrinkOrder, amountPaid: Double)
           (implicit preconditions: List[DrinkMakePrecondition] = List(),
            drinkMakeObservers: List[DrinkMakeObserver] = List()): Drink = {

    preconditions.foreach(_ (order.flavor))

    Cashier.charge(order.flavor, amountPaid)

    val drink = DrinkBuilder
      .withFlavor(order.flavor)
      .sugar(order.sugarCount)
      .temperature(order.temperature)
      .build

    drinkMakeObservers.foreach(_ (drink))

    drink
  }
}

case class Drink(flavor: String, sugarCount: Int, temperature: Temperature) {
  val stick: Option[Stick] = Option.when(sugarCount > 0)(Stick())
}
