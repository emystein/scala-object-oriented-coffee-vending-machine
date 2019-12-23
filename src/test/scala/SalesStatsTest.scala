import org.scalatest.FunSuite

class SalesStatsTest extends FunSuite {
  test("Group drink sales") {
    SalesStats.clear

    val coffee1 = DrinkMaker("C", 0, 0.6)
    val coffee2 = DrinkMaker("C", 0, 0.6)
    val tea1 = DrinkMaker("T", 0, 0.4)
    val tea2 = DrinkMaker("T", 0, 0.4)
    val chocolate1 = DrinkMaker("H", 0, 0.5)
    val chocolate2 = DrinkMaker("H", 0, 0.5)

    val report = SalesStats.currentStats

    val statItem = report.items.filter(item => item.flavor == "Coffee").head
    assert(statItem.salesCount == 2)
    assert(statItem.salesAmount == 1.2)

    val teaStatsItem = report.items.filter(item => item.flavor == "Tea").head
    assert(teaStatsItem.salesCount == 2)
    assert(teaStatsItem.salesAmount == 0.8)
    
    val chocolateStatsItem = report.items.filter(item => item.flavor == "Chocolate").head
    assert(chocolateStatsItem.salesCount == 2)
    assert(chocolateStatsItem.salesAmount == 1)
  }

  test("Total sales amount") {
    SalesStats.clear

    val coffee1 = DrinkMaker("C", 0, 0.6)
    val tea1 = DrinkMaker("T", 0, 0.4)
    val chocolate1 = DrinkMaker("H", 0, 0.5)

    val report = SalesStats.currentStats

    assert(report.totalSalesAmount == 1.5)
  }
}