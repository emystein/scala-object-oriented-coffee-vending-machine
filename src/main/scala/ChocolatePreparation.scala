class ChocolatePreparation extends DrinkPreparation {
  override def execute(sugarCount: Int, temperature: Temperature): Drink = Drink("Chocolate", sugarCount, temperature)
}
