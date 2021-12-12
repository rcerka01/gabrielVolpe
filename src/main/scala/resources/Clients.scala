package resources

import config.types
import cats.effect.std.Console
import cats.effect.{Concurrent, Resource}
import cats.syntax.all._
import config.types.{AppConfig, PostgreSQLConfig}
import eu.timepit.refined.auto._
import fs2.io.net.Network
import natchez.Trace.Implicits.noop
import org.typelevel.log4cats.Logger
import skunk._
import skunk.codec.text._
import skunk.implicits._

sealed abstract class Clients[F[_]](val postgres: Resource[F, Session[F]])

object Clients {

  def make[F[_]: Concurrent : Console : Logger : Network](cfg: AppConfig): Resource[F, Clients[F]] = {

    def checkPostgresConnection(postgres: Resource[F, Session[F]]): F[Unit] =
      postgres.use { session =>
        session.unique(sql"select version();".query(text)).flatMap( v =>
          Logger[F].info(s"Connected to postgres: $v")
        )
      }

    def mkPostgresConnection(pcfg: PostgreSQLConfig): SessionPool[F] =
      Session.pooled[F](
        host = pcfg.host.value,
        port = pcfg.port.value,
        user = pcfg.user.value,
        database = pcfg.database.value,
        password = Some(pcfg.password.value),
        max = pcfg.max.value
        // Console and Network
      ).evalTap(
        checkPostgresConnection
      )

    mkPostgresConnection(cfg.postgreSQL).map(new Clients[F](_){})
  }
}
