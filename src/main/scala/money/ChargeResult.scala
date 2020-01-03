package money

sealed trait ChargeResult

case class AmountNotSufficient(remaining: BigDecimal) extends ChargeResult

case class Change(change: BigDecimal) extends ChargeResult
