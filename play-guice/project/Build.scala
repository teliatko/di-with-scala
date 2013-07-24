import sbt._
import Keys._
import play.Project._

object ApplicationBuild extends Build {

  val appName         = "play-guice"
  val appVersion      = "1.0-SNAPSHOT"

  val appDependencies = Seq(
    // ScalaTest
    "org.scalatest" %% "scalatest" % "1.9.1" % "test",

    // Google Guice
    "com.google.inject" % "guice" % "3.0",
    "com.tzavellas" % "sse-guice" % "0.7.1"
    // You can also use
    // "net.codingwell" %% "scala-guice" % "3.0.2"
  )


  val main = play.Project(appName, appVersion, appDependencies).settings(
    testOptions in Test := Nil
  )

}
