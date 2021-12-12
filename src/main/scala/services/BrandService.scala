package services

import cats._
import cats.implicits._
import resources.Clients
import services.BrandService.BrandsSQL.selectAll
import shop.domain.Brands
import shop.domain.Brands._
import sql.codec._
import cats.effect._
import cats.syntax.all._
import skunk._
import skunk.implicits._

import java.util.UUID

object BrandService {
  import cats.implicits._

  implicit def apply[F[_]](implicit ev: Brands[F]): Brands[F] = ev

  object BrandsSQL {

    val codec: Codec[Brand] =
      (brandId ~ brandName).imap {
        case i ~ n => Brand(i, n)
      }(b => b.uuid ~ b.name)

    val selectAll =
      sql"""
           SELECT * FROM brands
         """.query(codec)
  }

  def impl[F[_] : MonadCancelThrow](clients: Clients[F]): Brands[F] = new Brands[F] {
    val postgres: Resource[F, Session[F]] = clients.postgres
    override def findAll: F[List[Brand]] = postgres.use(_.execute(BrandsSQL.selectAll))
    override def create(name: BrandName): F[BrandId] = BrandId(UUID.randomUUID()).pure[F]
  }


}
