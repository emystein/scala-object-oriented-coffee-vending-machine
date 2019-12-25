import org.scalatest.FunSuite

class DrinkOrderTest extends FunSuite {
  test("Order with no sugar") {
    val order = DrinkOrder("Coffee", 0)

    assert(order.sugarCount == 0)
    assert(!order.includeStick)
  }

  test("Order with 1 sugar") {
    val order = DrinkOrder("Coffee", 1)

    assert(order.sugarCount == 1)
    assert(order.includeStick)
  }
}
