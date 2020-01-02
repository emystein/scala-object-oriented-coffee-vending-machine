package money

case class Cashier(cashRegister: CashRegister) {
  def addCredit(value: Double): Unit = {
    cashRegister.addCredit(value)
  }

  def charge(flavor: String): BigDecimal = {
    val listPrice = DrinkPriceList.priceOf(flavor)

    if (cashRegister.credit < listPrice)
      throw AmountNotSufficientException(cashRegister.credit)

    val change = cashRegister.charge(listPrice)

    change
  }
}
