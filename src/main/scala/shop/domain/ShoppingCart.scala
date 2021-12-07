package shop.domain

import io.estatico.newtype.macros.newtype
import squants.market.Money

object ShoppingCart {

  import Items._
  import Users._

  @newtype case class Quantity(value: Int)
  @newtype case class Cart(items: Map[ItemId, Quantity])

  case class CartItem(item: Item, quantity: Quantity)
  case class CartTotal(items: List[CartItem], total: Money)

  trait ShoppingCart[F[_]] {
    def add(userId: UserId, itemId: ItemId): F[Unit]
    def get(userId: UserId): F[CartTotal]
    def delete(iserId: UserId): F[Unit]
    def removeItem(userId: UserId, itemId: ItemId): F[Unit]
    def update(userId: UserId, cart: Cart): F[Unit]
  }
}
