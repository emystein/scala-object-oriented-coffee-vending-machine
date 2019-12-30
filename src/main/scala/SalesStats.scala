import scala.collection.mutable

class SalesStats {
  var salesSumByFlavor: mutable.Map[String, DrinkStat] = mutable.HashMap()

  def add(drink: Drink, price: Double) = {
    val statItem = salesSumByFlavor.getOrElse(drink.flavor, new DrinkStat(drink.flavor, 0, 0))
    statItem.salesCount += 1
    statItem.salesAmount += price

    salesSumByFlavor.put(drink.flavor, statItem)
  }

  def currentStats: StatsReport = StatsReport(salesSumByFlavor.values.toList)
}
