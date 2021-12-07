package shop.domain

import io.estatico.newtype.macros.newtype
import squants.market.Money

import java.util.UUID

object PaymentClient {

  import Users._

  @newtype case class PaymentId(uuid: UUID)
  @newtype case class HoldersName(value: String)
  @newtype case class CardNumber(value: String)
  @newtype case class CardExpiration(value: String)
  @newtype case class Cvv(value: String)

  case class Card(name: HoldersName, number: CardNumber, expiration: CardExpiration, cvv: Cvv)
  case class Payment(id: UserId, total: Money, card: Card)

  trait PaymentClient[F[_]] {
    def process(payment: Payment): F[PaymentId]
  }
}
