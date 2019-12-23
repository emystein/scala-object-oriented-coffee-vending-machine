class StatsRecordDrinkMakeObserver extends DrinkMakeObserver {
  SalesStats.clear

  override def apply(drink: Drink, listPrice: Double): Unit = {
    SalesStats.add(drink, listPrice)
  }
}
