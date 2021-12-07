//package prerequests
//
//import cats.effect.std.{Semaphore, Supervisor}
//import cats.effect.{IO, IOApp}
//
//import scala.concurrent.duration._
//import scala.util.Random
//
//object Pre4SharedState {
//
//  def utilRandSleep: IO[Unit] =
//    IO(Random.nextInt(100)).flatMap { ms =>
//      IO.sleep( (ms + 700 ).millis)
//    }.void
//
//  def p1(s: Semaphore[IO]): IO[Unit] =
//    s.permit.surround(IO.println("Running p1")) >> utilRandSleep
//
//  def p2(s: Semaphore[IO]): IO[Unit] =
//    s.permit.surround(IO.println("Running p2")) >> utilRandSleep
//
//
//
//  def run: IO[Unit] =
//    Supervisor[IO].use { s =>
//      Semaphore[IO](1).flatMap { sem =>
//        s.supervise(p1(sem)).foreverM.void >>
//          s.supervise(p2(sem)).foreverM.void >>
//             IO.sleep(1.seconds).void
//      }
//    }
//}
