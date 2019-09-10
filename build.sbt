name := """splio-technical-test"""
organization := "com.tom-ish"

version := "1.0-SNAPSHOT"

lazy val root = (project in file(".")).enablePlugins(PlayScala)

scalaVersion := "2.13.0"

libraryDependencies += guice
libraryDependencies += "org.scalatestplus.play" %% "scalatestplus-play" % "4.0.3" % Test
libraryDependencies += ws
libraryDependencies += ehcache

// Adds additional packages into Twirl
//TwirlKeys.templateImports += "com.tom-ish.controllers._"

// Adds additional packages into conf/routes
// play.sbt.routes.RoutesKeys.routesImport += "com.tom-ish.binders._"
