import org.scalamock.scalatest.MockFactory
import org.scalatest.FunSuite

class DrinkShortageNotificationTest extends FunSuite with MockFactory {
  test("Drink shortage should be notified via e-mail") {
    implicit val beverageQuantityCheckerMock = stub[BeverageQuantityChecker]

    (beverageQuantityCheckerMock.isEmpty _) when("Coffee") returns(true)

    implicit val emailNotifierMock = mock[EmailNotifier]

    (emailNotifierMock.notifyMissingDrink _).expects("Coffee")

    DrinkMaker("Coffee", 0, 0.6)(drinkMakePreconditions = List(new BeverageQuantityPrecondition()))
  }

  test("Drink available should not notify shortage via e-mail") {
    implicit val beverageQuantityCheckerMock = stub[BeverageQuantityChecker]

    (beverageQuantityCheckerMock.isEmpty _) when("Coffee") returns(false)

    DrinkMaker("Coffee", 0, 0.6)(drinkMakePreconditions = List(new BeverageQuantityPrecondition()))
  }
}
