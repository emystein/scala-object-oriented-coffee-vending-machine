package stats

import machine.{DrinkMaker, DrinkOrder}
import org.scalatest.{BeforeAndAfterEach, FunSuite}

class SalesStatsTest extends FunSuite with BeforeAndAfterEach {
  test("Group drink sales") {
    val salesStats = new SalesStats

    val drinkMaker = new DrinkMaker(drinkMakeObservers = List(new StatsRecordDrinkMakeObserver(salesStats)))

    val coffee1 = drinkMaker.prepare(DrinkOrder("Coffee", 0))
    val coffee2 = drinkMaker.prepare(DrinkOrder("Coffee", 0))
    val tea1 = drinkMaker.prepare(DrinkOrder("Tea", 0))
    val tea2 = drinkMaker.prepare(DrinkOrder("Tea", 0))
    val chocolate1 = drinkMaker.prepare(DrinkOrder("Chocolate", 0))
    val chocolate2 = drinkMaker.prepare(DrinkOrder("Chocolate", 0))

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

    val drinkMaker = new DrinkMaker(drinkMakeObservers = List(new StatsRecordDrinkMakeObserver(salesStats)))

    val coffee1 = drinkMaker.prepare(DrinkOrder("Coffee", 0))
    val tea1 = drinkMaker.prepare(DrinkOrder("Tea", 0))
    val chocolate1 = drinkMaker.prepare(DrinkOrder("Chocolate", 0))

    val report = salesStats.currentStats

    assert(report.totalSalesAmount == 1.5)
  }
}
