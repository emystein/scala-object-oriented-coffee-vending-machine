class CoffeePreparation extends DrinkPreparation {
  override def execute(sugarCount: Int, extraHot: Boolean): Drink = Drink("Coffee", sugarCount, extraHot)
}
