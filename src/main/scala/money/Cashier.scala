package money

import machine.DrinkOrder

case class Cashier(cashRegister: CashRegister) {
  def addCredit(value: Double): Unit = {
    cashRegister.addCredit(value)
  }

  def charge(order: DrinkOrder): BigDecimal = {
    val ticket = Ticket(order)

    val amountRemaining = ticket.total - cashRegister.credit

    if (amountRemaining > 0) {
      throw AmountNotSufficientException(amountRemaining)
    }

    val change = cashRegister.charge(ticket.total)

    change
  }
}
