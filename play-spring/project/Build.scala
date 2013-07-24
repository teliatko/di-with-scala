import sbt._
import Keys._
import play.Project._

object ApplicationBuild extends Build {

  val appName         = "play-spring"
  val appVersion      = "1.0-SNAPSHOT"

  val appDependencies = Seq(
    // ScalaTest
    "org.scalatest" %% "scalatest" % "1.9.1" % "test",

    // Spring Framework
    "org.springframework" % "spring-context" % "3.2.2.RELEASE"
  )


  val main = play.Project(appName, appVersion, appDependencies).settings(
    testOptions in Test := Nil
  )

}
