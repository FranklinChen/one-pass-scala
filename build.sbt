name := "one-pass-scala"

organization := "com.franklinchen"

organizationHomepage := Some(url("http://franklinchen.com/"))

homepage := Some(url("http://github.com/FranklinChen/one-pass-scala"))

startYear := Some(2014)

description := "One pass lazy knot tying in Scala"

version := "1.0.0"

scalaVersion := "2.11.7"

scalacOptions ++= Seq(
  "-unchecked",
  "-deprecation",
  "-feature"
)

libraryDependencies ++= Seq(
  "org.spire-math" %% "cats" % "0.2.0",
  "org.scalaz" %% "scalaz-core" % "7.1.3",
  "org.scalacheck" %% "scalacheck" % "1.12.5" % Test,
  "org.specs2" %% "specs2-core" % "3.6.5" % Test
)
