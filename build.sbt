lazy val root = (project in file(".")).
  settings(
    inThisBuild(List(
      organization := "io.github.alexbergeron",
      scalaVersion := "2.12.3",
      version      := "0.1.0-SNAPSHOT",
      logLevel     := Level.Warn
    )),
    name := "kubeapi-talk",
    libraryDependencies += "io.fabric8" % "kubernetes-client" % "2.6.2"
  )
