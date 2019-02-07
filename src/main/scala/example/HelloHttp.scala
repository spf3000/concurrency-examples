package example

import scala.concurrent.{Future, ExecutionContext}
import scala.concurrent.Future._

object HelloHttp {
  import scala.concurrent.ExecutionContext.Implicits.global

  def hello1(n: Int) = Future {
    Thread.sleep(2000)
    println(s"hello from thread ${Thread.currentThread.getName()} future $n")
  }


}

