package shop.api

import cats.effect.{IO, Sync}
import org.http4s.HttpRoutes
import org.http4s.dsl.Http4sDsl
import org.http4s.circe.CirceEntityEncoder._
import config.Config._
import services.BrandService
import shop.domain.Brands._

import cats.Monad
import org.http4s._
import org.http4s.circe.CirceEntityEncoder._
import org.http4s.dsl.Http4sDsl
import org.http4s.server.Router

object BrandRoutes {
  import shop.domain.Brands._
  import cats.implicits._

  def brandRoutes[F[_] : Sync](H: Brands[F]): HttpRoutes[F] = {
    val dsl = new Http4sDsl[F] {}
    import dsl._
    HttpRoutes.of[F] {
      case GET -> Root / "brand" =>
        for {
          brands <- H.findAll
          resp <- Ok(brands)
        } yield resp
    }
  }
}
