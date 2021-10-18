name := "Lesson-7"
version := "0.1.0-SNAPSHOT"

scalaVersion := "2.13.2"
lazy val akkaVersion = "2.5.23"
lazy val akkaHttpVersion = "10.1.8"

val setLog4jDebug = sys.props("log4j2.debug") = "true"

libraryDependencies ++= Seq(
  "com.typesafe.scala-logging" %% "scala-logging" % "3.9.2",
  "org.slf4j" % "slf4j-api" % "1.7.30",
  "org.apache.logging.log4j" % "log4j-slf4j-impl" % "2.13.3",
  "com.typesafe" % "config" % "1.4.1",

  "io.bfil" %% "automapper" % "0.7.0",

  "com.typesafe.akka" %% "akka-slf4j" % akkaVersion,
  "com.typesafe.akka" %% "akka-stream" % akkaVersion,
  "com.typesafe.akka" %% "akka-testkit" % akkaVersion % Test,
  "com.typesafe.akka" %% "akka-stream-testkit" % akkaVersion % Test,
  "com.typesafe.akka" %% "akka-http" % akkaHttpVersion,
  "com.typesafe.akka" %% "akka-http-spray-json" % akkaHttpVersion,
  "com.typesafe.akka" %% "akka-http-xml" % akkaHttpVersion,
  "com.typesafe.akka" %% "akka-http-testkit" % akkaHttpVersion % Test,
  "com.typesafe.akka" %% "akka-http-caching" % akkaHttpVersion,
  "ch.qos.logback" % "logback-classic" % "1.0.0" % "runtime",

  "dev.optics" %% "monocle-core"  % "3.0.0",
  "dev.optics" %% "monocle-macro" % "3.0.0", // only for Scala 2.13

  "org.scalatest" %% "scalatest" % "3.1.0",
  "com.softwaremill.macwire" %% "macros" % "2.4.1" % "provided",

  "org.playframework.anorm" %% "anorm" % "2.6.10"
)
