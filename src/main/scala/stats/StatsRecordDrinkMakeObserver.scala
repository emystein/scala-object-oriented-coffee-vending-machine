package stats

import machine.{Drink, DrinkMakeObserver}
import money.DrinkPriceList

class StatsRecordDrinkMakeObserver(salesStats: SalesStats) extends DrinkMakeObserver {
  override def apply(drink: Drink): Unit = {
    salesStats.add(drink, DrinkPriceList.priceOf(drink.flavor))
  }
}
