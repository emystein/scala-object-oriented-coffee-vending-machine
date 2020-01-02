package money

import machine.DrinkOrder
import org.scalatest.FunSuite

class TicketTest extends FunSuite {
  test("Given an Order when create Ticket then the Ticket total should be the price list of the drink") {
    val ticket = Ticket(DrinkOrder("Coffee"))

    assert(ticket.total == DrinkPriceList.priceOf("Coffee"))
  }
}
