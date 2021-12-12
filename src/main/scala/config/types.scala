package config

import ciris._
import derevo.cats.show
import derevo.{Derivation, NewTypeDerivation, derive}
import eu.timepit.refined.types.all.{PosInt, UserPortNumber}
import eu.timepit.refined.types.string.NonEmptyString
import io.estatico.newtype.macros.newtype

import scala.annotation.implicitNotFound

object types {

  case class DBPassword(secret: NonEmptyString)

  case class PostgreSQLConfig(
     host: NonEmptyString,
     port: UserPortNumber,
     user: NonEmptyString,
     password: Secret[NonEmptyString],
     database: NonEmptyString,
     max: PosInt
  )

  case class AppConfig(
    postgreSQL: PostgreSQLConfig
  )

}
