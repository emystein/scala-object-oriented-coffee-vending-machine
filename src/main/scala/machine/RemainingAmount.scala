package machine

case class RemainingAmount(remaining: BigDecimal) extends PreparationResult {
  def ==(other: Double): Boolean = remaining == other
}
