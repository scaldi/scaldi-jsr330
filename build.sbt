name := "dipendi-jsr330"
organization := "com.protenus"

description := "dipendi-jsr330 - JSR 330 spec implementation for dipendi"
homepage := Some(url("https://github.com/protenus/dipendi"))
licenses := Seq("Apache License, ASL Version 2.0" -> url("https://www.apache.org/licenses/LICENSE-2.0"))

crossScalaVersions := Seq("2.11.12", "2.12.10", "2.13.1")
scalaVersion := "2.13.1"

scalacOptions ++= Seq("-deprecation", "-feature")
testOptions += Tests.Argument(TestFrameworks.JUnit, "-v")

libraryDependencies ++= Seq(
  "com.protenus" %% "dipendi" % "0.6.0",
  "javax.inject" % "javax.inject" % "1",

  "org.scalatest" %% "scalatest" % "3.1.0" % Test,
  "com.novocode" % "junit-interface" % "0.11" % Test,
  "javax.inject" % "javax.inject-tck" % "1" % Test
)

git.remoteRepo := "git@github.com:protenus/dipendi-jsr330.git"

// Site and docs

enablePlugins(SiteScaladocPlugin)
enablePlugins(GhpagesPlugin)

// nice *magenta* prompt!

shellPrompt in ThisBuild := { state =>
  scala.Console.MAGENTA + Project.extract(state).currentRef.project + "> " + scala.Console.RESET
}

// Publishing

publishArtifact in Test := false
pomIncludeRepository := (_ => false)

// Additional meta-info

startYear := Some(2015)
organizationHomepage := Some(url("https://github.com/protenus"))
scmInfo := Some(ScmInfo(
  browseUrl = url("https://github.com/protenus/dipendi-jsr330"),
  connection = "scm:git:git@github.com:protenus/dipendi-jsr330.git"
))
developers := List(
  Developer("AprilAtProtenus", "April Hyacinth", "april@protenus.com", url("https://github.com/AprilAtProtenus"))
)
