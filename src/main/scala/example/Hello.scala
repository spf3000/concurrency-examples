package example

import scala.concurrent.{Future, ExecutionContext}
import scala.concurrent.Future._
import java.util.concurrent.Executors

object Hello {

  implicit val ec: ExecutionContext = ExecutionContext.global

  def hello(n: Int)(implicit ec: ExecutionContext) = Future {
    Thread.sleep(2000)
    println(s"hello from thread ${Thread.currentThread.getName()} future $n")
  }

  def main(args: Array[String]): Unit = {

    val unboundedExecutor  = Executors.newCachedThreadPool()
    val fixedExecutor = Executors.newFixedThreadPool(1000)
    val blockPool = ExecutionContext.fromExecutor(unboundedExecutor)
    val fixedPool = ExecutionContext.fromExecutor(fixedExecutor)

    println(s"hello from thread ${Thread.currentThread.getName()} main method")
    val l = (1 to 1000000).toList
    val list = l.map(n => hello(n)(fixedPool))

    Future.sequence(list).onComplete{
      case either => unboundedExecutor.shutdown(); fixedExecutor.shutdown()
    }
    }

  }


