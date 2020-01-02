package money

import scala.math.BigDecimal.RoundingMode

object AmountNotSufficientException {
  def apply(amountRemaining: Double): AmountNotSufficientException = {
    new AmountNotSufficientException(BigDecimal(amountRemaining).setScale(1, RoundingMode.UP))
  }
}

case class AmountNotSufficientException(amountRemaining: BigDecimal) extends Exception("Amount not sufficient. Remaining: " + amountRemaining)
