addSbtPlugin("org.scala-js" % "sbt-scalajs" % "1.16.0")
addSbtPlugin("org.portable-scala" % "sbt-scalajs-crossproject" % "1.0.0")

// For TypeScript dependencies in client
addSbtPlugin("org.scalablytyped.converter" % "sbt-converter" % "1.0.0-beta42")

// For development reloading of server
addSbtPlugin("io.spray" % "sbt-revolver" % "0.10.0")
