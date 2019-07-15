import monix.eval._
import scala.concurrent.duration._
import scala.concurrent.ExecutionContext
import java.util.concurrent.Executors
import monix.execution.Scheduler

object TaskHello {

  def hello(n: Int) =
    Task {
      Thread.sleep(1000)
      println(s"hello from thread ${Thread.currentThread.getName()} task $n")
    }

  def main(args: Array[String]): Unit = {

    import monix.execution.Scheduler.Implicits.global
    val blockScheduler = Scheduler.io(name = "blocking-io")
    println(s"hello from thread ${Thread.currentThread.getName()} main method")
    val l = (1 to 1000).toList
    val source = Task.gather { l.map(n => hello(n)) }
    source
    //  .executeOn(blockScheduler)
    .runToFuture
      .onComplete(_ => sys.exit)
  }

}
