name := "Scala Spark Practice"

version := "0.1"

scalaVersion := "2.11.12"

version := "0.1.0-SNAPSHOT"

libraryDependencies += "org.apache.spark" % "spark-core_2.11" % "2.2.0"
libraryDependencies += "org.apache.spark" % "spark-sql_2.11" % "2.2.0"
libraryDependencies += "org.apache.spark" % "spark-mllib_2.11" % "2.2.0"
libraryDependencies += "org.apache.spark" % "spark-graphx_2.11" % "2.2.0"
libraryDependencies += "org.scalafx" %% "scalafx" % "12.0.2-R18"
resolvers += Resolver.sonatypeRepo("snapshots")

scalacOptions ++= Seq("-unchecked", "-deprecation", "-Xcheckinit", "-encoding", "utf8", "-feature")

// Fork a new JVM for 'run' and 'test:run', to avoid JavaFX double initialization problems
fork := true

// Determine OS version of JavaFX binaries
lazy val osName = System.getProperty("os.name") match {
  case n if n.startsWith("Linux") => "linux"
  case n if n.startsWith("Mac") => "mac"
  case n if n.startsWith("Windows") => "win"
  case _ => throw new Exception("Unknown platform!")
}

// Add JavaFX dependencies
lazy val javaFXModules = Seq("base", "controls", "fxml", "graphics", "media", "swing", "web")
libraryDependencies ++= javaFXModules.map( m=>
  "org.openjfx" % s"javafx-$m" % "12.0.2" classifier osName
)

