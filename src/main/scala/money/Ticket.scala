package money

import machine.DrinkOrder

case class Ticket(order: DrinkOrder) {
  val total = DrinkPriceList.priceOf(order.flavor)
}
