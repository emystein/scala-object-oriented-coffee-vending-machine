import org.scalatest.FunSuite

class SalesStatsTest extends FunSuite {
  test("Sum of drink sales") {
    SalesStats.clear

    val coffee1 = DrinkMaker("C", 0, 0.6)
    val coffee2 = DrinkMaker("C", 0, 0.6)

    val report = SalesStats.currentStats

    val statItem = report.items.head
    assert(statItem.flavor == "Coffee")
    assert(statItem.totalSalesAmount == 1.2)
  }
}
