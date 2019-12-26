object DrinkMaker {
  val drinkPreparationByFlavor = Map("Tea" -> new TeaPreparation, "Coffee" -> new CoffeePreparation, "Chocolate" -> new ChocolatePreparation, "Orange Juice" -> new OrangeJuicePreparation)

  def apply(flavor: String, sugarCount: Int = 0, amountPaid: Double, temperature: Temperature = NormalTemperature())
           (implicit drinkMakePreconditions: List[DrinkMakePrecondition] = List(),
            drinkMakeObservers: List[DrinkMakeObserver] = List()): Drink = {

    drinkMakePreconditions.foreach(_ (flavor))

    val drinkPreparation = drinkPreparationByFlavor(flavor)

    val drink = drinkPreparation.execute(sugarCount, temperature)

    Cashier.charge(drink.flavor, amountPaid)

    drinkMakeObservers.foreach(_ (drink))

    drink
  }
}

case class Drink(flavor: String, sugarCount: Int, temperature: Temperature) {
  val includeStick: Boolean = sugarCount > 0
}

