name := """to-bus"""
organization := "com.example"

version := "1.0-SNAPSHOT"

lazy val root = (project in file(".")).enablePlugins(PlayJava)
lazy val myProject = (project in file(".")).enablePlugins(PlayJava, PlayEbean)

scalaVersion := "2.12.8"

libraryDependencies += guice
libraryDependencies ++= Seq(evolutions, jdbc)
libraryDependencies += "org.postgresql" % "postgresql" % "42.2.5"

// https://mvnrepository.com/artifact/com.feth/play-authenticate
libraryDependencies += "com.feth" %% "play-authenticate" % "0.9.0"
libraryDependencies ++= Seq(
  "be.objectify" %% "deadbolt-java" % "2.6.1"
)

libraryDependencies += "com.typesafe.play" %% "play-mailer" % "6.0.1"
libraryDependencies += "com.typesafe.play" %% "play-mailer-guice" % "6.0.1"
libraryDependencies += "be.objectify" %% "deadbolt-java-gs" % "2.6.0"
libraryDependencies ++= Seq(
  ehcache
)