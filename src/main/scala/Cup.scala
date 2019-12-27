case class Cup(drink: Drink) {
  val stick: Option[Stick] = Option.when(drink.sugarCount > 0)(Stick())
}
