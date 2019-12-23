import scala.collection.mutable

object SalesStats {
  var salesSumByFlavor: mutable.Map[String, Double] = mutable.HashMap()

  def clear = salesSumByFlavor.clear

  def add(drink: Drink, price: Double) = {
    salesSumByFlavor.put(drink.flavor, salesSumByFlavor.getOrElse(drink.flavor, 0D) + price)
  }

  def currentStats: StatsReport = StatsReport(salesSumByFlavor.map(i => StatItem(i._1, i._2)).toList)
}
