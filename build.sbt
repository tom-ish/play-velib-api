name := """splio-technical-test"""
organization := "com.tom-ish"

version := "1.0-SNAPSHOT"

lazy val root = (project in file(".")).enablePlugins(PlayScala)

scalaVersion := "2.13.0"

libraryDependencies += guice
libraryDependencies += "org.scalatestplus.play" %% "scalatestplus-play" % "4.0.3" % Test
libraryDependencies += "com.adrianhurt" %% "play-bootstrap" % "1.5.1-P27-B4" // little bit of Bootstrap within Twirl templates
libraryDependencies += ws // this enables requesting velibs opendata's API calls
libraryDependencies += ehcache

// Adds additional packages into Twirl
//TwirlKeys.templateImports += "com.tom-ish.controllers._"

// Adds additional packages into conf/routes
// play.sbt.routes.RoutesKeys.routesImport += "com.tom-ish.binders._"
