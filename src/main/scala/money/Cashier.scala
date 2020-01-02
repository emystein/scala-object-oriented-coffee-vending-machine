package money

import machine.DrinkOrder

import scala.math.BigDecimal.RoundingMode

case class Cashier(cashRegister: CashRegister) {
  def addCredit(value: Double): Unit = {
    cashRegister.addCredit(value)
  }

  def charge(order: DrinkOrder): BigDecimal = {
    val ticket = Ticket(order)

    if (cashRegister.credit < ticket.total)
      throw AmountNotSufficientException(BigDecimal(ticket.total - cashRegister.credit).setScale(1, RoundingMode.UP))

    val change = cashRegister.charge(ticket.total)

    change
  }
}
