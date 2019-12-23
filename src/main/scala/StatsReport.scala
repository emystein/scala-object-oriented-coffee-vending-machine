case class StatsReport(items: List[DrinkStat]) {
  def totalSalesAmount = items.map(_.salesAmount).sum
}
