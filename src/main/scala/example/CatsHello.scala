import java.util.concurrent.Executors
import cats.effect.{ContextShift, IO}
import scala.concurrent.ExecutionContext
import cats.effect.Timer
import cats.effect.Clock
import cats.effect.IOApp
import cats.effect.ExitCode
import cats.implicits._

object CatsHello extends IOApp {
  val blockingEC =
    ExecutionContext.fromExecutor(Executors.newCachedThreadPool())

  def doSth(n: Int): IO[Unit] =
    IO(Thread.sleep(1000)).map(_ => println(s"hello from cats! $n"))

    def churn() =
      List.range(1,1000).map(n => contextShift.evalOn(blockingEC)(doSth(n))).parSequence


  override def run(args: List[String]): IO[ExitCode] =
    churn *> IO(ExitCode.Success)


}
//n => contextShift.evalOn(blockingEC)
