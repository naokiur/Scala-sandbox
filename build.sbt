name := "sandbox"

version := "1.0"

scalaVersion := "2.12.0"
scalacOptions ++= Seq("-deprecation", "-feature", "-unchecked", "-Xlint")

// Scala 2.10, 2.11
libraryDependencies ++= Seq(
    "org.scalikejdbc" %% "scalikejdbc"       % "2.5.0",
    "org.scalikejdbc" %% "scalikejdbc-config"       % "2.5.0",
    "com.h2database"  %  "h2"                % "1.4.193",
    "ch.qos.logback"  %  "logback-classic"   % "1.1.7"
)