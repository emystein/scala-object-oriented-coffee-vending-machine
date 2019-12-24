class ChocolatePreparation extends DrinkPreparation {
  override def execute(sugarCount: Int, extraHot: Boolean): Drink = Drink("Chocolate", sugarCount, extraHot)
}
