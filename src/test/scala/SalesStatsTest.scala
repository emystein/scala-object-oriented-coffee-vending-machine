import org.scalatest.{BeforeAndAfterEach, FunSuite}

class SalesStatsTest extends FunSuite with BeforeAndAfterEach {
  test("Group drink sales") {
    val salesStats = new SalesStats

    implicit val drinkMakeObservers = List(new StatsRecordDrinkMakeObserver(salesStats))

    val coffee1 = DrinkMaker(DrinkOrder("Coffee", 0), 0.6)
    val coffee2 = DrinkMaker(DrinkOrder("Coffee", 0), 0.6)
    val tea1 = DrinkMaker(DrinkOrder("Tea", 0), 0.4)
    val tea2 = DrinkMaker(DrinkOrder("Tea", 0), 0.4)
    val chocolate1 = DrinkMaker(DrinkOrder("Chocolate", 0), 0.5)
    val chocolate2 = DrinkMaker(DrinkOrder("Chocolate", 0), 0.5)

    val report = salesStats.currentStats

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
    val salesStats = new SalesStats

    implicit val drinkMakeObservers = List(new StatsRecordDrinkMakeObserver(salesStats))

    val coffee1 = DrinkMaker(DrinkOrder("Coffee", 0), 0.6)
    val tea1 = DrinkMaker(DrinkOrder("Tea", 0), 0.4)
    val chocolate1 = DrinkMaker(DrinkOrder("Chocolate", 0), 0.5)

    val report = salesStats.currentStats

    assert(report.totalSalesAmount == 1.5)
  }
}
