package money

import machine.DrinkOrder

import scala.math.BigDecimal.RoundingMode

case class Cashier(cashRegister: CashRegister) {
  def addCredit(value: Double): Unit = {
    cashRegister.addCredit(value)
  }

  def charge(order: DrinkOrder): ChargeResult = {
    val ticket = Ticket(order)

    val amountRemaining = BigDecimal(ticket.total - cashRegister.credit).setScale(1, RoundingMode.UP)

    if (amountRemaining > 0) {
      return AmountNotSufficient(amountRemaining)
    }

    Change(cashRegister.charge(ticket.total))
  }
}
