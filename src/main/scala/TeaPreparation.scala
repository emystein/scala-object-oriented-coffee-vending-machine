class TeaPreparation extends DrinkPreparation {
  override def execute(sugarCount: Int, temperature: Temperature): Drink = Drink("Tea", sugarCount, temperature)
}
