package com.lambdarat.quadmist

import com.lambdarat.quadmist.assets.{Assets, Fonts}
import com.lambdarat.quadmist.common.domain.{Board, Settings, Square}
import com.lambdarat.quadmist.models.QuadmistSetupData
import com.lambdarat.quadmist.sample.Sample
import com.lambdarat.quadmist.scenes.JoinScene
import indigo._
import indigo.scenes._

import scala.scalajs.js.annotation.JSExportTopLevel

@JSExportTopLevel("IndigoGame")
object Quadmist extends IndigoGame[Unit, QuadmistSetupData, Board, Unit] {

  def boot(flags: Map[String, String]): BootResult[Unit] = {
    val initialScreen = GameConfig.default.withViewport(1024, 768).withClearColor(ClearColor.White)
    BootResult(initialScreen, ())
      .withAssets(Assets.assets)
  }

  def initialScene(bootData: Unit): Option[SceneName] =
    Some(JoinScene.name)

  def scenes(bootData: Unit): NonEmptyList[Scene[QuadmistSetupData, Board, Unit]] =
    NonEmptyList(JoinScene)

  def setup(
      bootData: Unit,
      assetCollection: AssetCollection,
      dice: Dice
  ): Startup[QuadmistSetupData] = {
    val quadmistWS = WebSocketConfig(
      id = WebSocketId("quadmist"),
      address = s"ws://localhost:8080/join/${Sample.playerOneId.toUUID.show}"
    )

    val maybeFont = Fonts.buildFont(assetCollection)

    maybeFont match {
      case Some(font) =>
        Startup
          .Success(QuadmistSetupData(quadmistWS = quadmistWS))
          .addFonts(font)
      case None       => Startup.Failure("Failed to load font")
    }
  }

  def initialModel(startupData: QuadmistSetupData): Board = {
    Board(Array.empty[Array[Square]], Set.empty, Set.empty, Settings.default)
  }

  def initialViewModel(startupData: QuadmistSetupData, model: Board): Unit =
    ()
}
