package shop.api

import cats.effect.Sync
import org.http4s.HttpRoutes
import org.http4s.dsl.Http4sDsl

object TestRoutes {
  import shop.domain.TestDomain._
  import cats.implicits._

  def helloWorldRoutes[F[_] : Sync](H: TestAlgebra[F]): HttpRoutes[F] = {
    val dsl = new Http4sDsl[F] {}
    import dsl._
    HttpRoutes.of[F] {
      case GET -> Root / "hello" / name =>
        for {
          greeting <- H.hello(Name(name))
          resp <- Ok(greeting)
        } yield resp
    }
  }
}
