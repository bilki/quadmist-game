lazy val indigoV = "0.5.0"

lazy val commonSettings = Seq(
  scalaVersion := "2.13.3",
  scalacOptions += "-Ymacro-annotations",
  version := "0.1.0-SNAPSHOT",
  organization := "com.lambdarat"
)

lazy val quadmist = (project in file("."))
  .settings(commonSettings)
  .settings(
    test in `quadmist-common` := {}
  )
  .dependsOn(`quadmist-game`)
  .aggregate(`quadmist-common`, `quadmist-game`)

lazy val `quadmist-common` = ProjectRef(file("quadmist-common"), "quadmist-common-subJS")

lazy val `quadmist-game` = project
  .enablePlugins(ScalaJSPlugin, SbtIndigo)
  .settings(commonSettings)
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

addCommandAlias("buildGame", "project quadmist-game;compile;fastOptJS;indigoBuild;project /")
addCommandAlias("runGame", "project quadmist-game;compile;fastOptJS;indigoRun;project /")
