package money

case class AmountNotSufficientException(amountRemaining: BigDecimal) extends Exception("Amount not sufficient. Remaining: " + amountRemaining)
