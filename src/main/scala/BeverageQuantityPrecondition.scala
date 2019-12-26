class BeverageQuantityPrecondition(implicit beverageQuantityChecker: BeverageQuantityChecker, emailNotifier: EmailNotifier)
  extends DrinkMakePrecondition {

  def apply(flavor: String): Unit = {
    if (beverageQuantityChecker.isEmpty(flavor))
      emailNotifier.notifyMissingDrink(flavor)
  }
}
