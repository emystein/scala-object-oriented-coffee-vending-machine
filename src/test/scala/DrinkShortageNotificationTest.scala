import org.scalamock.scalatest.MockFactory
import org.scalatest.FunSuite

class DrinkShortageNotificationTest extends FunSuite with MockFactory {
  val beverageQuantityCheckerMock = stub[BeverageQuantityChecker]
  val emailNotifierMock = mock[EmailNotifier]
  val drinkMaker = new DrinkMaker(preconditions = List(new BeverageQuantityPrecondition(beverageQuantityCheckerMock, emailNotifierMock)))

  test("Drink available should not notify shortage via e-mail") {
    (beverageQuantityCheckerMock.isEmpty _) when("Coffee") returns(false)

    drinkMaker.prepare(DrinkOrder("Coffee", 0), 0.6)
  }

  test("Drink shortage should be notified via e-mail") {
    (beverageQuantityCheckerMock.isEmpty _) when("Coffee") returns(true)

    (emailNotifierMock.notifyMissingDrink _).expects("Coffee")

    drinkMaker.prepare(DrinkOrder("Coffee", 0), 0.6)
  }
}
