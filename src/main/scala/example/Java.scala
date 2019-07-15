object Java {

  def hello(n: Int) =
    new Thread {
      override def run(): Unit = {
              Thread.sleep(1000)
        println(
          s"hello from thread ${Thread.currentThread.getName()} java $n"
        )
      }
    }

  def main(args: Array[String]): Unit = {
    for (i <- 1 to 100) {
      val thread1 = hello(i)
      thread1.start
    }
    sys.exit()
  }

}
