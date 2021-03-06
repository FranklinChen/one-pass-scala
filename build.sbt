name := "one-pass-scala"

organization := "com.franklinchen"

organizationHomepage := Some(url("http://franklinchen.com/"))

homepage := Some(url("http://github.com/FranklinChen/one-pass-scala"))

startYear := Some(2014)

description := "One pass lazy knot tying in Scala"

version := "1.0.0"

scalaVersion := "2.12.8"

scalacOptions ++= Seq(
  "-unchecked",
  "-deprecation",
  "-feature",
  "-Ypartial-unification"
)

libraryDependencies ++= Seq(
  "org.typelevel" %% "cats-core" % "1.5.0",
  "org.specs2" %% "specs2-core" % "4.3.5" % Test
)
