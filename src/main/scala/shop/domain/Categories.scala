package shop.domain

import io.estatico.newtype.macros.newtype
import java.util.UUID

object Categories {

  @newtype case class CategoryId(value: UUID)
  @newtype case class CategoryName(value: String)

  case class Category(uuid: CategoryId, name: CategoryName)

  trait Categories[F[_]] {
    def findAll: F[List[Category]]
    def create(name: CategoryName): F[CategoryId]
  }
}
