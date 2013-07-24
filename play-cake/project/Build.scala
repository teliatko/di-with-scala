import sbt._
import Keys._
import play.Project._

object ApplicationBuild extends Build {

  val appName         = "play-cake"
  val appVersion      = "1.0-SNAPSHOT"

  val appDependencies = Seq(
    // ScalaTest
    "org.scalatest" %% "scalatest" % "1.9.1" % "test"

    // No dependencies, plain Scala
  )


  val main = play.Project(appName, appVersion, appDependencies).settings(
    testOptions in Test := Nil
  )

}
