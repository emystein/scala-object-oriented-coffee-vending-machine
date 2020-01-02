package machine.preparation

sealed trait Temperature

case class NormalTemperature() extends Temperature

case class ExtraHotTemperature() extends Temperature

