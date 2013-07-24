import sbt._
import Keys._
import play.Project._

object ApplicationBuild extends Build {

  val appName         = "play-spring-scala"
  val appVersion      = "1.0-SNAPSHOT"

  val appDependencies = Seq(
    // ScalaTest
    "org.scalatest" %% "scalatest" % "1.9.1" % "test",

    // Spring-Scala
    // (-) not final, you ahev to work with snapshot
    "org.springframework.scala" % "spring-scala" % "1.0.0.BUILD-SNAPSHOT"
  )



  val main = play.Project(appName, appVersion, appDependencies).settings(
    testOptions in Test := Nil,
    resolvers ++= Seq(
      "SpringSource Milestones"    at "http://repo.springsource.org/snapshot/"
    )
  )

}
