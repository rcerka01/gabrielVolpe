//package prerequests
//
//import cats.{Functor, MonadThrow}
//import cats.implicits.{catsSyntaxApplicativeErrorId, catsSyntaxApplicativeId, toFunctorOps}
//import prerequests.Pre5errorHandling.BusinessError.RandomError
//
//import scala.util.Random
//import scala.util.control.NoStackTrace
//
//object Pre5errorHandling {
//
//  // MONAD / APPLICATIVE ERROR
//  class Category
//
//  trait Categories[F[_]] {
//    def findAll: F[List[Category]]
//  }
//
////  if you need to deal with error, you have to use trait like this. It can be good idea not care with error cases,
////  deal only successful ones an let HTTP framework to deal with errors
////  trait Categories[F[_]] {
////    def findAll: F[Either[RandomError, List[Category]]]
////  }
//
//  sealed trait BusinessError extends NoStackTrace
//  object BusinessError {
//    type RandomError = RandomError.type
//    case object RandomError extends BusinessError
//  }
//
////  object Categories {
////    def make[F[_] : MonadThrow : Random]: Categories[F] =
////      new Categories[F] {
////        override def findAll: F[List[Category]] =
////          Random.nextInt(100) match {
////            case n if n > 50 => List.empty[Category].pure[F]
////            case _ => RandomError.raiseError[F, List[Category]]
////          }
////      }
////  }
//
//  // EITHER MONAD ERROR
//
//  // if code logic is dependant of error appearance
//
////  class Program[F[_] : Functor](categories: Categories[F]) {
////    def findAll: F[List[Category]] =
////      categories.maybeFindAll.map {
////        case Right(c) => c
////        case Left(e) => List.empty[Category]
////
////      }
////  }
//
//}
