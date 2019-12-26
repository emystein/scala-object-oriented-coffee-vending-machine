class BeverageQuantityPrecondition(implicit beverageQuantityChecker: BeverageQuantityChecker = null,
                                   emailNotifier: EmailNotifier = null)
  extends DrinkMakePrecondition {

  def apply(flavor: String): Unit = {
    if (beverageQuantityChecker != null && beverageQuantityChecker.isEmpty(flavor))
      emailNotifier.notifyMissingDrink(flavor)
  }
}
