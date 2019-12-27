import org.scalamock.scalatest.MockFactory
import org.scalatest.FunSuite

class DrinkShortageNotificationTest extends FunSuite with MockFactory {
  implicit val beverageQuantityCheckerMock = stub[BeverageQuantityChecker]
  implicit val emailNotifierMock = mock[EmailNotifier]

  test("Drink available should not notify shortage via e-mail") {
    (beverageQuantityCheckerMock.isEmpty _) when("Coffee") returns(false)

    DrinkMaker(DrinkOrder("Coffee", 0), 0.6)(preconditions = List(new BeverageQuantityPrecondition()))
  }

  test("Drink shortage should be notified via e-mail") {
    (beverageQuantityCheckerMock.isEmpty _) when("Coffee") returns(true)

    (emailNotifierMock.notifyMissingDrink _).expects("Coffee")

    DrinkMaker(DrinkOrder("Coffee", 0), 0.6)(preconditions = List(new BeverageQuantityPrecondition()))
  }
}
