package machine.preparation.preconditions

trait DrinkMakePrecondition {
  def apply(flavor: String): Unit
}
