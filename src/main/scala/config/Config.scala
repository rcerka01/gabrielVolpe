package config

import config.types._

import scala.concurrent.duration._
import cats.effect.Async
import cats.syntax.all._
import ciris._
import ciris.refined._
import com.comcast.ip4s._
import config.Environment.{Prod, Test}
import eu.timepit.refined.auto._
import eu.timepit.refined.cats._
import eu.timepit.refined.types.string.NonEmptyString


object Config {

  def load[F[_] : Async]: F[AppConfig] =
    env("APP_ENV")
      .as[Environment]
      .flatMap {
        case Prod =>
            (
              env("DB_PASSWORD").as[NonEmptyString].secret
            ).map { dbPassword =>
            AppConfig(
              PostgreSQLConfig(
                host = "localhost",
                port = 5432,
                user = "gabrielvolpe",
                password = dbPassword,
                database = "gvdb",
                max = 10
              )
            )
          }

        //case _ =>
      }
      .load[F]

}
