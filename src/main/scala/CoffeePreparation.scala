class CoffeePreparation extends DrinkPreparation {
  override def execute(sugarCount: Int, temperature: Temperature): Drink = Drink("Coffee", sugarCount, temperature)
}
