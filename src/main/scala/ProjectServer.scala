import cats.effect.{Async, Resource}
import com.comcast.ip4s._
import fs2.Stream
import org.http4s.ember.client.EmberClientBuilder
import org.http4s.ember.server.EmberServerBuilder
import org.http4s.implicits._
import org.http4s.server.middleware.Logger
import resources.Clients
import services.{BrandService, TestService}
import shop.api.{BrandRoutes, TestRoutes}

object ProjectServer {

  def stream[F[_]: Async](clients: Clients[F]): Stream[F, Nothing] = {
  //def stream[F[_]: Async]: Stream[F, Nothing] = {
    import cats.implicits._
    for {
      _ <- Stream.resource(EmberClientBuilder.default[F].build)
      helloWorldAlg = TestService.impl[F]
      brandsAlg = BrandService.impl[F](clients)
      httpApp = ( TestRoutes.helloWorldRoutes[F](helloWorldAlg) <+> BrandRoutes.brandRoutes(brandsAlg)).orNotFound

      finalHttpApp = Logger.httpApp(true, true)(httpApp)

      exitCode <- Stream.resource(
        EmberServerBuilder.default[F]
          .withHost(ipv4"0.0.0.0")
          .withPort(port"8080")
          .withHttpApp(finalHttpApp)
          .build >>
          Resource.eval(Async[F].never)
      )
    } yield exitCode
  }.drain

}
