package services

import cats.Applicative

object TestService {
  import shop.domain.TestDomain._
  import cats.implicits._

  implicit def apply[F[_]](implicit ev: TestAlgebra[F]): TestAlgebra[F] = ev

  def impl[F[_] : Applicative]: TestAlgebra[F] = new TestAlgebra[F] {
    def hello(n: Name): F[Greeting] =
      Greeting("Hello, " + n.name).pure[F]
  }
}
