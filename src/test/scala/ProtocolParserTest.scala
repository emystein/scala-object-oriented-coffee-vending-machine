import org.scalatest.FunSuite

class ProtocolParserTest extends FunSuite {
  val parser = new ProtocolParser()

  test("Parse Tea command") {
    val parsedOrder = parser.parse("T:1:0")

    assert(parsedOrder.flavor == "Tea")
  }

  test("Parse Coffee command") {
    val parsedOrder = parser.parse("C:1:0")

    assert(parsedOrder.flavor == "Coffee")
  }

  test("Parse Chocolate command") {
    val parsedOrder = parser.parse("H:1:0")

    assert(parsedOrder.flavor == "Chocolate")
  }

  test("Parse Orange Juice command") {
    val parsedOrder = parser.parse("O::")

    assert(parsedOrder.flavor == "Orange Juice")
  }

  test("Parse drink command with no sugar") {
    val parsedOrder = parser.parse("H::")

    assert(parsedOrder.sugarCount == 0)
  }

  test("Parse drink command with 1 sugar") {
    val parsedOrder = parser.parse("T:1:0")

    assert(parsedOrder.sugarCount == 1)
  }

  test("Parse extra hot drink command") {
    val parsedOrder = parser.parse("Th:1:0")

    assert(parsedOrder.temperature.isInstanceOf[ExtraHotTemperature])
  }

}
