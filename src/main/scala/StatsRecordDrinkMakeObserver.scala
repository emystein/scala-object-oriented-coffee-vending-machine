class StatsRecordDrinkMakeObserver(salesStats: SalesStats) extends DrinkMakeObserver {
  override def apply(drink: Drink, listPrice: Double): Unit = {
    salesStats.add(drink, listPrice)
  }
}
