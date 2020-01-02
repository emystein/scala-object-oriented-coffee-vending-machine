package money

class Cashier(cashRegister: CashRegister) {
  def addCredit(value: Double): Unit = {
    cashRegister.addCredit(value)
  }

  def charge(flavor: String, amountPaid: Double): BigDecimal = {
    val listPrice = DrinkPriceList.priceOf(flavor)

    if (amountPaid < listPrice)
      throw AmountNotSufficientException(amountPaid)

    val change = cashRegister.charge(listPrice)

    change
  }
}
