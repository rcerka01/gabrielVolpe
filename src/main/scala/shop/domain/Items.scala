package shop.domain

import io.estatico.newtype.macros.newtype
import squants.Money

import java.util.UUID

object Items {

  @newtype case class ItemId(value: UUID)
  @newtype case class ItemName(value: String)
  @newtype case class ItemDescription(value: String)

  import Brands._
  import Categories._

  case class Item(
                   uuid: ItemId,
                   name: ItemName,
                   description: ItemDescription,
                   price: Money,
                   brand: Brand,
                   category: Category)

  case class CreateItem(
                         name: ItemName,
                         description: ItemDescription,
                         brandId: BrandId,
                         categoryId: CategoryId
                       )

  case class UpateItem(id: ItemId, price: Money)

  trait Items[F[_]] {
    def findAll: F[List[Item]]
    def findBy(brand: BrandName): F[List[Item]]
    def findById(brandId: BrandId): F[Option[Item]]
    def create(item: CreateItem): F[ItemId]
    def update(item: UpateItem): F[Unit]
  }
}
