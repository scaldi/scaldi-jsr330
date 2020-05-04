name := "scaldi-jsr330"
organization := "org.scaldi"

description := "scaldi-jsr330 - JSR 330 spec implementation for scaldi"
homepage := Some(url("https://github.com/scaldi/scaldi-jsr330"))
licenses := Seq("Apache License, ASL Version 2.0" -> url("https://www.apache.org/licenses/LICENSE-2.0"))

crossScalaVersions := Seq("2.11.12", "2.12.10", "2.13.1")
scalaVersion := "2.13.1"

mimaPreviousArtifacts := Set().map(organization.value %% name.value % _)

scalacOptions ++= Seq("-deprecation", "-feature")
testOptions += Tests.Argument(TestFrameworks.JUnit, "-v")

libraryDependencies ++= Seq(
  "org.scaldi" %% "scaldi" % "0.6.0",
  "javax.inject" % "javax.inject" % "1",

  "org.scalatest" %% "scalatest" % "3.1.1" % Test,
  "com.novocode" % "junit-interface" % "0.11" % Test,
  "javax.inject" % "javax.inject-tck" % "1" % Test
)

git.remoteRepo := "git@github.com:scaldi/scaldi-jsr330.git"

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
organizationHomepage := Some(url("https://github.com/scaldi"))
scmInfo := Some(ScmInfo(
  browseUrl = url("https://github.com/protenus/scaldi-jsr330"),
  connection = "scm:git:git@github.com:scaldi/scaldi-jsr330.git"
))
developers := List(
  Developer("AprilAtProtenus", "April Hyacinth", "april@protenus.com", url("https://github.com/AprilAtProtenus")),
  Developer("dave-handy", "Dave Handy", "wdhandy@gmail.com", url("https://github.com/dave-handy"))
)
