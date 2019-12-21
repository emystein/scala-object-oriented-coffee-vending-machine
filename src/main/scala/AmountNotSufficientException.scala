case class AmountNotSufficientException(amountGiven: Double) extends Exception("Amount not sufficient: " + amountGiven)
