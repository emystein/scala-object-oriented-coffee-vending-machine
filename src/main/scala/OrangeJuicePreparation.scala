class OrangeJuicePreparation extends DrinkPreparation {
  override def execute(sugarCount: Int, temperature: Temperature): Drink = Drink("Orange Juice", sugarCount, temperature)
}
