name := "animalWellbeing"

val deployFast = taskKey[Unit]("Copies the fastLinkJS script to deployscripts/")
val deployFull = taskKey[Unit]("Copies the fullLinkJS script to deployscripts/")

import org.scalajs.linker.interface.ModuleSplitStyle

ThisBuild / scalaVersion := "3.3.0"

lazy val root = project.in(file("."))
  .aggregate(commonJS, commonJVM, awServer, awClient)

lazy val common = crossProject(JVMPlatform, JSPlatform).in(file("common"))
  .settings(
    libraryDependencies += "org.scala-js" %% "scalajs-stubs" % "1.1.0" % "provided"

  )
lazy val commonJS = common.js
lazy val commonJVM = common.jvm

lazy val awClient = project.in(file("client"))
  .enablePlugins(ScalaJSPlugin)
  .enablePlugins(ScalablyTypedConverterExternalNpmPlugin)
  .settings(

    resolvers ++= Resolver.sonatypeOssRepos("snapshots"),
    libraryDependencies ++= Seq(
      "com.wbillingsley" %%% "doctacular" % "0.3.0+2-7eae7e5d-SNAPSHOT",
      "com.lihaoyi" %%% "upickle" % "3.1.0",
    ),

    // This is an application with a main method
    scalaJSUseMainModuleInitializer := true,
    
    // For vite bundler
    scalaJSLinkerConfig ~= {
      _.withModuleKind(ModuleKind.ESModule)
        .withModuleSplitStyle(ModuleSplitStyle.SmallModulesFor(List("animalWellbeing"))) 
    },

    // To use ScalablyTypedConverterExternalNpmPlugin
    externalNpm := {
      baseDirectory.value
    },

    // Used by GitHub Actions to get the script out from the .gitignored target directory
    deployFast := {
      val opt = (Compile / fastOptJS).value
      IO.copyFile(opt.data, new java.io.File("target/compiled.js"))
    },

    deployFull := {
      val opt = (Compile / fullOptJS).value
      IO.copyFile(opt.data, new java.io.File("target/compiled.js"))
    }
  )


lazy val awServer = project.in(file("server"))
  .settings(
    libraryDependencies ++= Seq(
      "dev.zio" %% "zio-http" % "3.0.0-RC2"
    )
  )


