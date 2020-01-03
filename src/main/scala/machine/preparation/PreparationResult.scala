package machine.preparation

import machine.Cup

sealed trait PreparationResult

case class DrinkNotSelected() extends PreparationResult

case class RemainingAmount(remaining: BigDecimal) extends PreparationResult {
  def ==(other: Double): Boolean = remaining == other
}

case class CupAndChange(cup: Cup, change: BigDecimal) extends PreparationResult
