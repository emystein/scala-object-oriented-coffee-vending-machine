class TeaPreparation extends DrinkPreparation {
  override def execute(sugarCount: Int, extraHot: Boolean): Drink = Drink("Tea", sugarCount, extraHot)
}
