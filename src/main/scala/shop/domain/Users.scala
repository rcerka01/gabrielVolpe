package shop.domain

import io.estatico.newtype.macros.newtype

import java.util.UUID

object Users {

  @newtype case class UserName(value: String)
  @newtype case class UserId(value: UUID)
  @newtype case class Password(value: String)
  @newtype case class EncryptedPassword(value: String)

  case class User(id: UserId, name: UserName)
  case class UserWithPassword(id: UserId, name: UserName, password: EncryptedPassword)

  trait Users[F[_]] {
    def find(username: UserName): F[UserWithPassword]
    def create(userName: UserName, password: EncryptedPassword): F[UserId]
  }
}
