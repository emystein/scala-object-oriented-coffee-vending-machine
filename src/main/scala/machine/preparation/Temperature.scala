package machine.preparation

sealed trait Temperature

case object NormalTemperature extends Temperature

case object ExtraHotTemperature extends Temperature

