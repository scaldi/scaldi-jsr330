name := "scaldi-jsr330"
organization := "org.scaldi"
version := "0.5.7-SNAPSHOT"

description := "scaldi-jsr330 - JSR 330 spec implementation for scaldi"
homepage := Some(url("http://scaldi.org"))
licenses := Seq("Apache License, ASL Version 2.0" -> url("http://www.apache.org/licenses/LICENSE-2.0"))

scalaVersion := "2.11.6"
scalacOptions ++= Seq("-deprecation", "-feature")
testOptions += Tests.Argument(TestFrameworks.JUnit, "-v")

libraryDependencies ++= Seq(
  "org.scaldi" %% "scaldi" % "0.5.6",
  "javax.inject" % "javax.inject" % "1",

  "org.scalatest" %% "scalatest" % "2.2.1" % "test",
  "com.novocode" % "junit-interface" % "0.11" % "test",
  "javax.inject" % "javax.inject-tck" % "1" % "test"
)

git.remoteRepo := "git@github.com:scaldi/scaldi-jsr330.git"
resolvers ++= Seq(
  "Typesafe Releases Repository" at "http://repo.typesafe.com/typesafe/releases/",
  "Scalaz Bintray Repo" at "http://dl.bintray.com/scalaz/releases")

// Site and docs

site.settings
site.includeScaladoc()
ghpages.settings

// nice *magenta* prompt!

shellPrompt in ThisBuild := { state =>
  scala.Console.MAGENTA + Project.extract(state).currentRef.project + "> " + scala.Console.RESET
}

// Publishing

publishMavenStyle := true
publishArtifact in Test := false
pomIncludeRepository := (_ => false)
publishTo := Some(
  if (version.value.trim.endsWith("SNAPSHOT"))
    "snapshots" at "https://oss.sonatype.org/content/repositories/snapshots"
  else
    "releases" at "https://oss.sonatype.org/service/local/staging/deploy/maven2")

// Additional meta-info

startYear := Some(2015)
organizationHomepage := Some(url("https://github.com/scaldi"))
scmInfo := Some(ScmInfo(
  browseUrl = url("https://github.com/scaldi/scaldi-jsr330"),
  connection = "scm:git:git@github.com:scaldi/scaldi-jsr330.git"
))
pomExtra := <xml:group>
  <developers>
    <developer>
      <id>OlegIlyenko</id>
      <name>Oleg Ilyenko</name>
    </developer>
  </developers>
</xml:group>
