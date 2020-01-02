package money

import machine.DrinkOrder

case class Cashier(cashRegister: CashRegister) {
  def addCredit(value: Double): Unit = {
    cashRegister.addCredit(value)
  }

  def charge(order: DrinkOrder): BigDecimal = {
    val ticket = Ticket(order)

    if (cashRegister.credit < ticket.total)
      throw AmountNotSufficientException(ticket.total - cashRegister.credit)

    val change = cashRegister.charge(ticket.total)

    change
  }
}
