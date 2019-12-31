class DrinkMaker(preconditions: List[DrinkMakePrecondition] = List(), drinkMakeObservers: List[DrinkMakeObserver] = List()) {
  def prepare(order: DrinkOrder): Cup = {
    preconditions.foreach(_ (order.flavor))

    val drink = DrinkMixer
      .flavor(order.flavor)
      .sugarCount(order.sugarCount)
      .temperature(order.temperature)
      .build

    drinkMakeObservers.foreach(_ (drink))

    Cup(drink)
  }
}