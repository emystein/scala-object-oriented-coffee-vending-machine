package money

import scala.math.BigDecimal.RoundingMode

class CashRegister {
  var credit: Double = 0

  def addCredit(anAmount: Double): Unit = credit += anAmount

  def charge(value: Double): BigDecimal = {
    val change = BigDecimal(credit - value).setScale(1, RoundingMode.UP)
    credit = 0
    change
  }
}
