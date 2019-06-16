import Dependencies._

ThisBuild / scalaVersion     := "2.12.8"
ThisBuild / version          := "0.1.0-SNAPSHOT"
ThisBuild / organization     := "com.example"
ThisBuild / organizationName := "example"

lazy val root = (project in file("."))
  .settings(
    name := "concurrency-examples",
    libraryDependencies ++= Seq(
    scalaTest % Test,
    "com.typesafe.akka" %% "akka-http" % "10.0.10",
    "io.monix" %% "monix" % "3.0.0-RC2",
    "org.typelevel" %% "cats-effect" % "1.3.1"
)
  )
