name in ThisBuild := """quadmist"""
scalaVersion in ThisBuild := "2.13.3"
version in ThisBuild := "0.1.0-SNAPSHOT"
organization in ThisBuild := "com.lambdarat"

lazy val indigoV = "0.5.0"

lazy val quadmist = (project in file("."))
  .dependsOn(`quadmist-game`)
  .aggregate(`quadmist-common`, `quadmist-game`)

lazy val `quadmist-common` = ProjectRef(file("quadmist-common"), "quadmist-common")

lazy val `quadmist-game` = project
  .enablePlugins(ScalaJSPlugin, SbtIndigo)
  .settings(
    libraryDependencies ++= Seq(
      "io.indigoengine" %%% "indigo"            % indigoV,
      "io.indigoengine" %%% "indigo-json-circe" % indigoV,
      "io.indigoengine" %%% "indigo-extras"     % indigoV
    ),
    showCursor := true,
    title := "Quadmist",
    gameAssetsDirectory := "assets",
    windowStartWidth := 1024,
    windowStartHeight := 768
  )
  .dependsOn(`quadmist-common`)

scalacOptions += "-Ymacro-annotations"

addCommandAlias("buildGame", "project quadmist-game;compile;fastOptJS;indigoBuild")
addCommandAlias("runGame", "project quadmist-game;compile;fastOptJS;indigoRun")
