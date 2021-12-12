package shop.domain

import derevo.cats.{eqv, show}
import derevo.circe.magnolia.{decoder, encoder}
import io.estatico.newtype.macros.newtype
import derevo.derive
import monocle.Iso

import java.util.UUID

/////////////////////////////////////////

import scala.annotation.implicitNotFound

import derevo.{ Derivation, NewTypeDerivation }

trait Derive[F[_]] extends Derivation[F] with NewTypeDerivation[F] {
  def instance(implicit ev: OnlyNewtypes): Nothing = ev.absurd

  @implicitNotFound("Only newtypes instances can be derived")
  abstract final class OnlyNewtypes {
    def absurd: Nothing = ???
  }
}

/////////////////////////////////////////
trait IsUUID[A] {
  def _UUID: Iso[UUID, A]
}

object IsUUID {
  def apply[A: IsUUID]: IsUUID[A] = implicitly

  implicit val identityUUID: IsUUID[UUID] = new IsUUID[UUID] {
    val _UUID = Iso[UUID, UUID](identity)(identity)
  }
}

object uuid extends Derive[IsUUID]


//////////////////////

object Brands {
  @derive(decoder, encoder, eqv, show, uuid)
  @newtype
  case class BrandId(value: UUID)

  @derive(decoder, encoder, eqv, show)
  @newtype
  case class BrandName(value: String)

  @derive(decoder, encoder, eqv, show)
  case class Brand(uuid: BrandId, name: BrandName)


  trait Brands[F[_]] {
    def findAll: F[List[Brand]]
    def create(name: BrandName): F[BrandId]
  }
}
