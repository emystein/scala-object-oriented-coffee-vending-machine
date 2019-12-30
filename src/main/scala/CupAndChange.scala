case class CupAndChange(cup: Option[Cup], change: BigDecimal) extends VendingProcessResult {
  override val pendingAmount: Double = 0
}
