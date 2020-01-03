package machine.preparation

import machine.preparation.preconditions.DrinkMakePrecondition
import machine.{Cup, Drink, DrinkOrder}

class DrinkMaker(preconditions: List[DrinkMakePrecondition] = List(), drinkMakeObservers: List[DrinkMakeObserver] = List()) {
  def prepare(order: DrinkOrder): Cup = {
    preconditions.foreach(_ (order.flavor))

    val drink = Drink(order.flavor, order.sugarCount, order.temperature)

    drinkMakeObservers.foreach(_ (drink))

    Cup(drink)
  }
}