import scala.concurrent.{Future, ExecutionContext}
import scala.concurrent.Future._
import scala.concurrent._
import scala.concurrent.duration._
import java.util.concurrent.Executors

object FutureHello {

  def hello(n: Int)(implicit ec: ExecutionContext) = Future {
    Thread.sleep(2000)
    println(s"hello from thread ${Thread.currentThread.getName()} future $n")
  }

  def main(args: Array[String]): Unit = {
    implicit val ec: ExecutionContext = ExecutionContext.global

    println(s"hello from thread ${Thread.currentThread.getName()} main method")

    val l = (1 to 10).toList
    val list = l.map(n => hello(n)) //(fixedPool))
    Future.sequence(list)

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
    //   Future.sequence(list)
    //     .onComplete{
    //     case either => unboundedExecutor.shutdown(); fixedExecutor.shutdown()
    //   }
  }

}
