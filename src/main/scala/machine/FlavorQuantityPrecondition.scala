package machine

class FlavorQuantityPrecondition(flavorQuantityChecker: FlavorQuantityChecker, emailNotifier: EmailNotifier)
  extends DrinkMakePrecondition {

  def apply(flavor: String): Unit = {
    if (flavorQuantityChecker.isEmpty(flavor))
      emailNotifier.notifyMissingDrink(flavor)
  }
}