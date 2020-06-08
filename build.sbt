name in ThisBuild := """quadmist"""
scalaVersion in ThisBuild := "2.13.2"
version in ThisBuild := "0.1.0-SNAPSHOT"
organization in ThisBuild := "com.lambdarat"

lazy val indigoV = "0.1.0"

lazy val quadmist = (project in file("."))
  .enablePlugins(ScalaJSPlugin, SbtIndigo)
  .settings(
    libraryDependencies ++= Seq(
      "io.indigoengine" %%% "indigo"            % indigoV,
      "io.indigoengine" %%% "indigo-json-circe" % indigoV,
      memeid,
      newtype,
      enumeratum,
      mouse,
      scalacheck          % Test
    ) ++ scalatest ++ cats,
    kindProjector,
    showCursor := true,
    title := "Quadmist",
    gameAssetsDirectory := "assets"
  )

scalacOptions += "-Ymacro-annotations"
