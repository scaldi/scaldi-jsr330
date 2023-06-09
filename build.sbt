name         := "scaldi-jsr330"
organization := "org.scaldi"

description := "scaldi-jsr330 - JSR 330 spec implementation for scaldi"
homepage    := Some(url("https://github.com/scaldi/scaldi-jsr330"))
licenses := Seq(
  "Apache License, ASL Version 2.0" -> url("https://www.apache.org/licenses/LICENSE-2.0")
)

scalaVersion          := "2.13.11"
crossScalaVersions    := Seq("2.11.12", "2.12.15", "2.13.11")
mimaPreviousArtifacts := Set("0.6.0", "0.6.1").map(organization.value %% name.value % _)
scalacOptions ++= Seq("-deprecation", "-feature")

libraryDependencies ++= Seq(
  "org.scaldi"    %% "scaldi"           % "0.6.2",
  "javax.inject"   % "javax.inject"     % "1",
  "org.scalatest" %% "scalatest"        % "3.2.12" % Test,
  "com.github.sbt" % "junit-interface"  % "0.13.3" % Test,
  "javax.inject"   % "javax.inject-tck" % "1"      % Test
)

git.remoteRepo := "git@github.com:scaldi/scaldi-jsr330.git"

// Publishing

pomIncludeRepository   := (_ => false)
Test / publishArtifact := false
ThisBuild / githubWorkflowTargetTags ++= Seq("v*")
ThisBuild / githubWorkflowScalaVersions         := crossScalaVersions.value
ThisBuild / githubWorkflowJavaVersions          := Seq("8", "11").map(JavaSpec(JavaSpec.Distribution.Adopt, _))
ThisBuild / githubWorkflowPublishTargetBranches := Seq(RefPredicate.StartsWith(Ref.Tag("v")))
ThisBuild / githubWorkflowBuildPreamble         := Seq(WorkflowStep.Sbt(List("scalafmtCheckAll")))
ThisBuild / githubWorkflowPublish := Seq(
  WorkflowStep.Sbt(
    List("ci-release"),
    env = Map(
      "PGP_PASSPHRASE"    -> "${{ secrets.PGP_PASSPHRASE }}",
      "PGP_SECRET"        -> "${{ secrets.PGP_SECRET }}",
      "SONATYPE_PASSWORD" -> "${{ secrets.SONATYPE_PASSWORD }}",
      "SONATYPE_USERNAME" -> "${{ secrets.SONATYPE_USERNAME }}"
    )
  )
)

// Site and docs

enablePlugins(SiteScaladocPlugin)
enablePlugins(GhpagesPlugin)

// nice *magenta* prompt!

ThisBuild / shellPrompt := { state =>
  scala.Console.MAGENTA + Project.extract(state).currentRef.project + "> " + scala.Console.RESET
}

// Additional meta-info

startYear            := Some(2015)
organizationHomepage := Some(url("https://github.com/scaldi"))
scmInfo := Some(
  ScmInfo(
    browseUrl = url("https://github.com/protenus/scaldi-jsr330"),
    connection = "scm:git:git@github.com:scaldi/scaldi-jsr330.git"
  )
)
developers := List(
  Developer(
    "AprilAtProtenus",
    "April Hyacinth",
    "april@protenus.com",
    url("https://github.com/AprilAtProtenus")
  ),
  Developer("dave-handy", "Dave Handy", "wdhandy@gmail.com", url("https://github.com/dave-handy"))
)

scalafmtCheckAll := {
  (Compile / scalafmtSbtCheck).value
  scalafmtCheckAll.value
}

scalafmtAll := {
  (Compile / scalafmtSbt).value
  scalafmtAll.value
}
