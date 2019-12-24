object DrinkMaker {
  val drinkPreparationByFlavorCode = Map("T" -> new TeaPreparation, "C" -> new CoffeePreparation, "H" -> new ChocolatePreparation, "O" -> new OrangeJuicePreparation)

  def apply(flavorCode: String, sugarCount: Int = 0, amountPaid: Double, extraHot: Boolean = false)
           (implicit drinkMakeObservers: List[DrinkMakeObserver] = List(),
            beverageQuantityChecker: BeverageQuantityChecker = null,
            emailNotifier: EmailNotifier = null): Drink = {

    val drinkPreparation = drinkPreparationByFlavorCode(flavorCode)

    val drink = drinkPreparation.execute(sugarCount, extraHot)

    if (beverageQuantityChecker != null && beverageQuantityChecker.isEmpty(drink.flavor))
      emailNotifier.notifyMissingDrink(drink.flavor)

    val listPrice = DrinkPriceList.priceOf(drink.flavor)

    if (amountPaid < listPrice)
     throw AmountNotSufficientException(amountPaid)

    drinkMakeObservers.foreach(_(drink, listPrice))

    drink
  }
}

case class Drink(flavor: String, sugarCount: Int, extraHot: Boolean) {
  val includeStick: Boolean = sugarCount > 0
}

