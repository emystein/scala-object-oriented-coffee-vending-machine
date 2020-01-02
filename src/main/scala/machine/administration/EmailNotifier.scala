package machine.administration

trait EmailNotifier {
  def notifyMissingDrink(drink: String)
}
