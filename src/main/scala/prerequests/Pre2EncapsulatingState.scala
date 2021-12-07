//package prerequests
//
//import cats.Functor
//import cats.effect.{IO, Sync}
//import cats.effect.kernel.Ref
//import cats.implicits._
//
//object Pre2EncapsulatingState {
//
//            // OLD VERSION
//            object LiveCounter {
//              def make[F[_]: Sync]: F[Counter[F]] =
//                Ref.of[F, Int](0).map(new LiveCounter[F](_))
//            }
//            class LiveCounter[F[_]] private (
//                                              ref: Ref[F, Int]
//                                            ) extends Counter[F] {
//              def incr: F[Unit] = ref.update(_ + 1)
//              def get: F[Int]
//              = ref.get
//            }
//
//  // IN MEMORY COUNTER NEW
//  trait Counter[F[_]] {
//    def incr: F[Unit]
//    def get: F[Int]
//  }
//
//  object Counter {
//    def make[F[_] : Functor : Ref.Make]: F[Counter[F]]  =
//      Ref.of[F, Int](0).map { ref =>
//        new Counter[F] {
//          override def incr: F[Unit] = ref.update(_ + 1)
//          override def get: F[Int] = ref.get
//        }
//      }
//  }
//
//  def program(c: Counter[IO]): IO[Unit] =
//    for {
//      _ <- c.get.flatMap(IO.println)
//      _ <- c.incr
//      _ <- c.get.flatMap(IO.println)
//      _ <- c.incr.replicateA(5).void
//      _ <- c.get.flatMap(IO.println)
//    } yield ()
//
//
//}
