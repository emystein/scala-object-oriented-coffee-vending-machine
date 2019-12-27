object DrinkMaker {
  def apply(order: DrinkOrder, amountPaid: Double)
           (implicit preconditions: List[DrinkMakePrecondition] = List(),
            drinkMakeObservers: List[DrinkMakeObserver] = List()): Cup = {

    preconditions.foreach(_ (order.flavor))

    Cashier.charge(order.flavor, amountPaid)

    val drink = DrinkMixer
      .withFlavor(order.flavor)
      .sugar(order.sugarCount)
      .temperature(order.temperature)
      .build

    drinkMakeObservers.foreach(_ (drink))

    Cup(drink)
  }
}