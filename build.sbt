ThisBuild / name := "solr.almaren"
ThisBuild / organization := "com.github.music-of-the-ainur"

lazy val scala211 = "2.11.12"
lazy val scala212 = "2.12.10"

crossScalaVersions := Seq(scala211,scala212)
ThisBuild / scalaVersion := scala212

val sparkVersion = "2.4.4"

scalacOptions ++= Seq("-deprecation", "-feature")

libraryDependencies ++= Seq(
  "org.apache.spark" %% "spark-core" % sparkVersion % "provided",
  "org.apache.spark" %% "spark-sql" % sparkVersion % "provided",
  "com.github.music-of-the-ainur" %% "almaren-framework" % "0.2.3-2-4" % "provided",
  "it.agilelab.bigdata.spark" % "spark-solr" % "3.8.1" % "provided" excludeAll(
    ExclusionRule(organization = "org.apache.hadoop"),
    ExclusionRule(organization = "org.apache.spark"),
    ExclusionRule(organization = "com.fasterxml.jackson.core")
  ),
  // Mising library from spark-solr connector
  "commons-httpclient" % "commons-httpclient" % "3.1",
  "com.typesafe.scala-logging" %% "scala-logging" % "3.9.0",
  "org.scalatest" %% "scalatest" % "3.0.5" % "test",
  "com.fasterxml.jackson.module" %% "jackson-module-scala" % "2.9.9",
  "com.fasterxml.jackson.core" % "jackson-core" % "2.9.9",
  "com.fasterxml.jackson.core" % "jackson-databind" % "2.9.9",
  "it.agilelab.bigdata.spark" % "spark-solr" % "3.8.1" % "test" excludeAll(
    ExclusionRule(organization = "org.apache.hadoop"),
    ExclusionRule(organization = "org.apache.spark"),
    ExclusionRule(organization = "com.fasterxml.jackson.module"),
    ExclusionRule(organization = "com.fasterxml.jackson.core")
  )
)

enablePlugins(GitVersioning)

resolvers += "maven-restlet" at "http://maven.restlet.org"

resolvers +=
  "Sonatype OSS Snapshots" at "https://oss.sonatype.org/service/local/repositories/releases/content"

ThisBuild / scmInfo := Some(
  ScmInfo(
    url("https://github.com/music-of-the-ainur/solr.almaren"),
    "scm:git@github.com:music-of-the-ainur/solr.almaren.git"
  )
)
ThisBuild / developers := List(
  Developer(
    id    = "mantovani",
    name  = "Daniel Mantovani",
    email = "daniel.mantovani@modak.com",
    url   = url("https://github.com/music-of-the-ainur")
  )
)

ThisBuild / description := "Solr Connector For Almaren Framework"
ThisBuild / licenses := List("Apache 2" -> new URL("http://www.apache.org/licenses/LICENSE-2.0.txt"))
ThisBuild / homepage := Some(url("https://github.com/music-of-the-ainur/solr.almaren"))
ThisBuild / organizationName := "Music of Ainur"
ThisBuild / organizationHomepage := Some(url("https://github.com/music-of-the-ainur"))


// Remove all additional repository other than Maven Central from POM
ThisBuild / pomIncludeRepository := { _ => false }
ThisBuild / publishTo := {
  val nexus = "https://oss.sonatype.org/"
  if (isSnapshot.value) Some("snapshots" at nexus + "content/repositories/snapshots")
  else Some("releases" at nexus + "service/local/staging/deploy/maven2")
}

ThisBuild / publishMavenStyle := true
updateOptions := updateOptions.value.withGigahorse(false)
