package money

import machine.DrinkOrder

case class Cashier(cashRegister: CashRegister) {
  def addCredit(value: Double): Unit = {
    cashRegister.addCredit(value)
  }

  def charge(order: DrinkOrder): BigDecimal = {
    val listPrice = DrinkPriceList.priceOf(order.flavor)

    if (cashRegister.credit < listPrice)
      throw AmountNotSufficientException(cashRegister.credit)

    val change = cashRegister.charge(listPrice)

    change
  }
}
