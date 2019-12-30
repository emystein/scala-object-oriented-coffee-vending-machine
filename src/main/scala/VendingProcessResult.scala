trait VendingProcessResult {
  val pendingAmount: Double
  val cup: Option[Cup]
  val change: BigDecimal
}
