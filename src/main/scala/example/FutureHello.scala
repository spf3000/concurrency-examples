import scala.concurrent.{Future, ExecutionContext}
import scala.concurrent.Future._
import scala.concurrent._
import scala.concurrent.duration._
import java.util.concurrent.Executors

object FutureHello {

  def hello(n: Int)(implicit ec: ExecutionContext) = Future {
//    blocking {
    Thread.sleep(2000)
    println(s"hello from thread ${Thread.currentThread.getName()} future $n")
//    }
  }

  def main(args: Array[String]): Unit = {
    implicit val ec: ExecutionContext = ExecutionContext.global

    for {
      _ <- Future(println("porca"))
      _ <- Future(println("miseria"))
    } yield ()

    println(s"hello from thread ${Thread.currentThread.getName()} main method")

    val l = (1 to 100).toList
    val list = l.map(n => hello(n)) //(fixedPool))
    Await.ready(Future.sequence(list), 20 seconds)

    //    blocking {
    //}

// Await.ready(  , 20 seconds)
//      .onComplete{
//      case either => fixedExecutor.shutdown()
//        unboundedExecutor.shutdown();
//    }

//     val fixedExecutor = Executors.newFixedThreadPool(100)
//     val fixedPool = ExecutionContext.fromExecutor(fixedExecutor)
//    val unboundedExecutor  = Executors.newCachedThreadPool()
//    val blockPool = ExecutionContext.fromExecutor(unboundedExecutor)
//
//   Future.sequence(list)
//     .onComplete{
//     case either => unboundedExecutor.shutdown(); fixedExecutor.shutdown()
//   }
  }

}
