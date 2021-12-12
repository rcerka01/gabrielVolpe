import cats.effect.{ExitCode, IO, IOApp}
import config.Config
import cats.effect._
import cats.effect.std.Supervisor
import org.typelevel.log4cats.{Logger, SelfAwareStructuredLogger}
import org.typelevel.log4cats.slf4j.Slf4jLogger
import resources.Clients

object Main extends IOApp {

  override def run(args: List[String]): IO[ExitCode] = {
    implicit val logger: SelfAwareStructuredLogger[IO] = Slf4jLogger.getLogger[IO]

    Config.load[IO].flatMap { cfg =>
      Logger[IO].info(s"Logger works: ") >>
        Supervisor[IO].use { implicit sp =>
          Clients.make[IO](cfg).use { client =>
            ProjectServer.stream[IO](client).compile.drain.as(ExitCode.Success)
          }
        }
      }
    }

}
