object DrinkMaker {
  val drinkPreparationByFlavor = Map("Tea" -> new TeaPreparation, "Coffee" -> new CoffeePreparation, "Chocolate" -> new ChocolatePreparation, "Orange Juice" -> new OrangeJuicePreparation)

  def apply(flavor: String, sugarCount: Int = 0, amountPaid: Double, extraHot: Boolean = false)
           (implicit drinkMakeObservers: List[DrinkMakeObserver] = List(),
            beverageQuantityChecker: BeverageQuantityChecker = null,
            emailNotifier: EmailNotifier = null): Drink = {

    val drinkPreparation = drinkPreparationByFlavor(flavor)

    val drink = drinkPreparation.execute(sugarCount, extraHot)

    if (beverageQuantityChecker != null && beverageQuantityChecker.isEmpty(drink.flavor))
      emailNotifier.notifyMissingDrink(drink.flavor)

    Cashier.charge(drink.flavor, amountPaid)

    drinkMakeObservers.foreach(_(drink))

    drink
  }
}

case class Drink(flavor: String, sugarCount: Int, extraHot: Boolean) {
  val includeStick: Boolean = sugarCount > 0
}

