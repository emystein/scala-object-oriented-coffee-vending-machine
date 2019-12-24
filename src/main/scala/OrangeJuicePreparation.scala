class OrangeJuicePreparation extends DrinkPreparation {
  override def execute(sugarCount: Int, extraHot: Boolean): Drink = Drink("Orange Juice", sugarCount, extraHot)
}
