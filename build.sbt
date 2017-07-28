name := "one-pass-scala"

organization := "com.franklinchen"

organizationHomepage := Some(url("http://franklinchen.com/"))

homepage := Some(url("http://github.com/FranklinChen/one-pass-scala"))

startYear := Some(2014)

description := "One pass lazy knot tying in Scala"

version := "1.0.0"

scalaVersion := "2.12.3"

scalacOptions ++= Seq(
  "-unchecked",
  "-deprecation",
  "-feature"
)

libraryDependencies ++= Seq(
  "org.typelevel" %% "cats" % "0.9.0",
  "org.specs2" %% "specs2-core" % "3.9.4" % Test
)
