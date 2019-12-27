class DrinkMaker(preconditions: List[DrinkMakePrecondition] = List(), drinkMakeObservers: List[DrinkMakeObserver] = List()) {
  def prepare(order: DrinkOrder, amountPaid: Double): Cup = {
    preconditions.foreach(_ (order.flavor))

    Cashier.charge(order.flavor, amountPaid)

    val drink = DrinkMixer
      .flavor(order.flavor)
      .sugarCount(order.sugarCount)
      .temperature(order.temperature)
      .build

    drinkMakeObservers.foreach(_ (drink))

    Cup(drink)
  }
}