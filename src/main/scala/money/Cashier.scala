package money

import scala.math.BigDecimal.RoundingMode

object Cashier {
  def charge(flavor: String, amountPaid: Double): BigDecimal = {
    val listPrice = DrinkPriceList.priceOf(flavor)

    if (amountPaid < listPrice)
      throw AmountNotSufficientException(amountPaid)

    BigDecimal(amountPaid - listPrice).setScale(1, RoundingMode.UP)
  }
}
