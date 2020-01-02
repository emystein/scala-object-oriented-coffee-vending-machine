package machine.preparation

import machine.Drink

trait DrinkMakeObserver {
  def apply(drink: Drink)
}
