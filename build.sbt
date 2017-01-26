name := """play-java"""

version := "1.0-SNAPSHOT"

lazy val root = (project in file(".")).enablePlugins(PlayJava)

scalaVersion := "2.11.8"

libraryDependencies += javaJdbc
libraryDependencies += javaJpa
libraryDependencies += "org.hibernate" % "hibernate-core" % "5.2.5.Final"
libraryDependencies += cache
libraryDependencies += javaWs
libraryDependencies += "org.mockito" % "mockito-core" % "2.1.0"