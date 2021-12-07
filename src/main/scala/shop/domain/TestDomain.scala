package shop.domain

import io.circe.{Encoder, Json}
import org.http4s.EntityEncoder
import org.http4s.circe.jsonEncoderOf

object TestDomain {
  final case class Name(name: String) extends AnyVal
  final case class Greeting(greeting: String) extends AnyVal

  trait TestAlgebra[F[_]] {
    def hello(n: Name): F[Greeting]
  }

  implicit def greetingEntityEncoder[F[_]]: EntityEncoder[F, Greeting] =
    jsonEncoderOf[F, Greeting]

  object Greeting {
    implicit val greetingEncoder: Encoder[Greeting] = new Encoder[Greeting] {
      final def apply(a: Greeting): Json = Json.obj(
        ("message", Json.fromString(a.greeting)),
      )
    }
  }
}
