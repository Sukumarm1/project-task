name := """play-crud-application"""
organization := "com.qbrainx"

version := "1.0-SNAPSHOT"

lazy val root = (project in file(".")).enablePlugins(PlayScala)

scalaVersion := "2.13.8"

libraryDependencies += guice


libraryDependencies += "com.h2database" % "h2" % "2.1.210"
libraryDependencies += "com.typesafe.play" %% "play-slick" % "5.0.0"
libraryDependencies += "com.typesafe.play" %% "play-slick-evolutions" % "5.0.0"
libraryDependencies += "mysql" % "mysql-connector-java" % "8.0.28"
