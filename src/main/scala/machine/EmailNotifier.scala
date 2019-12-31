package machine

trait EmailNotifier {
  def notifyMissingDrink(drink: String)
}
