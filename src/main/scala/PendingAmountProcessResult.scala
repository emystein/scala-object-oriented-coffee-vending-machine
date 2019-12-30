case class PendingAmountProcessResult(pendingAmount: Double) extends VendingProcessResult {
  override val cup: Option[Cup] = None
  override val change: BigDecimal = 0
}
