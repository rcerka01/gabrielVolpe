//package prerequests
//
//object Pre1StronglyTypedFunctions {
//
//  case class User(username: String, email: String)
//
//  trait SomeThing[F[_]] {
//    def lookup(username: String, email: String): F[Option[User]]
//  }
//
//  // VALUE CLASSES
//  case class Username1(val value: String) extends AnyVal
//  case class Email1(val value: String) extends AnyVal
//
//  // better option
//  sealed abstract class Username2(val value: String)
//  sealed abstract class Email2(val value: String)
//  // still memory issues
//
//  // NEWTYPES
//  // zero cost wrappers
//  import io.estatico.newtype.macros._
//  @newtype case class Username3(value: String)
//  @newtype case class Email3(value: String)
//
//  // antipatern
//  // import io.estatico.newtype.ops._
//  // "foo".coerce[User]
//
//  // still validation needed
//
//  // REFINMENT TYPES
//  // validate input
//  // eg. non empty string
//  import eu.timepit.refined.api.Refined
//  import eu.timepit.refined.types.string.NonEmptyString
//  @newtype case class Username4(value: NonEmptyString)
//  @newtype case class Email4(value: NonEmptyString)
//
//  // RUNTIME VALIDATION
//  // ???
//
//  val u = Username4(NonEmptyString("x"))
//  // val u2:Username4 = Username4("d") ??
//  println(u)
//}
