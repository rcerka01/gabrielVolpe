package shop.domain

import cats.data.NonEmptyList
import io.estatico.newtype.macros.newtype
import squants.market.Money

import java.util.UUID

object Orders {

  @newtype case class OrderId(uuid: UUID)

  import Users._
  import Items._
  import PaymentClient._
  import ShoppingCart._

  case class Order(id: OrderId, pid: PaymentId, items: Map[ItemId, Quantity], total: Money)

  trait Orders[F[_]] {
    def get(userId: UserId, orderId: OrderId): F[Option[Order]]
    def findBy(userId: UserId): F[List[Order]]
    def create(userId: UserId, paymentId: PaymentId, items: NonEmptyList[CartItem], total: Money): F[OrderId]
  }
}
