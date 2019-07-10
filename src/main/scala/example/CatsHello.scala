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

  def blockingOp = IO(Thread.sleep(1000))
  def doSth(n: Int): IO[Unit] = IO {
    println(s"hello from cats! $n")
  }

  //start with doSth
  def churn() =
    List
      .range(1, 1000)
      .map(doSthBlock)
      .sequence

  def doSthBlock(n: Int) =
    for {
      _ <- contextShift.evalOn(blockingEC)(blockingOp)
      _ <- doSth(n)
    } yield ()

  override def run(args: List[String]): IO[ExitCode] =
    churn *> IO(ExitCode.Success)
//    for {
//      _ <- contextShift.evalOn(blockingEC)(blockingOp)
//      _ <- churn
//    } yield (ExitCode.Success)

}
