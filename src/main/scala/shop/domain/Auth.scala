package shop.domain

import io.estatico.newtype.macros.newtype

object Auth {

  import Users._

  @newtype case class JwtToken(value: String)

  trait Auth[F[_]] {
    def findUser(token: JwtToken): F[Option[User]]
    def newUser(userName: UserName, password: Password): F[JwtToken]
    def login(userName: UserName, password: Password): F[JwtToken]
    def logout(token: JwtToken, username: UserName): F[Unit]
  }
}
