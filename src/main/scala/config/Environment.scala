package config

// both from ciris enumeratum
import enumeratum.EnumEntry._
import enumeratum._

sealed abstract class Environment extends EnumEntry with Lowercase

object Environment extends Enum[Environment] with CirisEnum[Environment] {
  case object Test extends Environment
  case object Prod extends Environment

  val values = findValues
}
