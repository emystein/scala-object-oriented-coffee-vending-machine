package machine.preparation.preconditions

import machine.preparation.DrinkMaker
import machine.DrinkOrder
import machine.administration.EmailNotifier
import org.scalamock.scalatest.MockFactory
import org.scalatest.FunSuite

class DrinkShortageNotificationTest extends FunSuite with MockFactory {
  val flavorQuantityChecker = stub[FlavorQuantityChecker]
  val emailNotifier = mock[EmailNotifier]
  val drinkMaker = new DrinkMaker(preconditions = List(new FlavorQuantityPrecondition(flavorQuantityChecker, emailNotifier)))

  test("machine.Drink available should not notify shortage via e-mail") {
    (flavorQuantityChecker.isEmpty _) when("Coffee") returns(false)

    drinkMaker.prepare(DrinkOrder("Coffee", 0))
  }

  test("machine.Drink shortage should be notified via e-mail") {
    (flavorQuantityChecker.isEmpty _) when("Coffee") returns(true)

    (emailNotifier.notifyMissingDrink _).expects("Coffee")

    drinkMaker.prepare(DrinkOrder("Coffee", 0))
  }
}
