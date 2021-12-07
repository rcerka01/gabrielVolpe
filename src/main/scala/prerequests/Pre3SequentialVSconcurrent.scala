//package prerequests
//
//import cats.data.State
//
//object Pre3SequentialVSconcurrent {
//
//  // SEQUENTIAL
//  val nextInt: State[Int, Int] = State( x => (x+1, x*2) )
//
//  // each flatmap here performs sequential
//  def seqv: State[Int, Int] =
//    for {
//      n1 <- nextInt
//      n2 <- nextInt
//      n3 <- nextInt
//    } yield (n1 + n2 + n3)
//
//  // CONCURRENCY
//  // use Ref
//
//}
