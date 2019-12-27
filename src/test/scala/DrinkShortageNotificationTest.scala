import org.scalamock.scalatest.MockFactory
import org.scalatest.FunSuite

class DrinkShortageNotificationTest extends FunSuite with MockFactory {
  val flavorQuantityChecker = stub[FlavorQuantityChecker]
  val emailNotifier = mock[EmailNotifier]
  val drinkMaker = new DrinkMaker(preconditions = List(new FlavorQuantityPrecondition(flavorQuantityChecker, emailNotifier)))

  test("Drink available should not notify shortage via e-mail") {
    (flavorQuantityChecker.isEmpty _) when("Coffee") returns(false)

    drinkMaker.prepare(DrinkOrder("Coffee", 0), 0.6)
  }

  test("Drink shortage should be notified via e-mail") {
    (flavorQuantityChecker.isEmpty _) when("Coffee") returns(true)

    (emailNotifier.notifyMissingDrink _).expects("Coffee")

    drinkMaker.prepare(DrinkOrder("Coffee", 0), 0.6)
  }
}
