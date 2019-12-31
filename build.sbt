lazy val root = (project in file(".")).
  settings(
    inThisBuild(List(
      organization := "com.example",
      scalaVersion := "2.13.1"
    )),
    name := "scala-object-oriented-coffee-vending-machine"
  )

libraryDependencies += "com.google.guava" % "guava" % "28.1-jre"

libraryDependencies += "org.scalatest" %% "scalatest" % "3.0.8" % Test
libraryDependencies += "org.scalamock" %% "scalamock" % "4.4.0" % Test