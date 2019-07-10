import scalaz.zio.App
import scalaz.zio._
import scalaz.zio.console._
import scala.concurrent.ExecutionContext
import java.util.concurrent.Executors
import scalaz.zio.internal.NamedThreadFactory
import scalaz._
import java.util.concurrent.TimeUnit
import scalaz.zio.clock.Clock

object ZioHello extends App {

  val ec1 = ExecutionContext.fromExecutor(Executors.newCachedThreadPool())

  val x: ZIO[Console with Clock, Nothing, List[Unit]] =
    ZIO
      .foreachPar(List.range(1, 1000))(n => {
        putStrLn(s"hellofrom zio Thread ${Thread.currentThread.getName} $n")
      }.delay(duration.Duration(2000, TimeUnit.MILLISECONDS)))

  override def run(args: List[String]) = {
    x.fold(_ => 0, _ => 0)
  }

}
