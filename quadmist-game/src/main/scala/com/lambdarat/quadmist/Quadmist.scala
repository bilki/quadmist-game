package com.lambdarat.quadmist

import com.lambdarat.quadmist.assets.Assets
import com.lambdarat.quadmist.models.QuadmistSetupData
import com.lambdarat.quadmist.scenes.EmptyScene
import indigo._
import indigo.scenes._

import scala.scalajs.js.annotation.JSExportTopLevel

@JSExportTopLevel("IndigoGame")
object Quadmist extends IndigoGame[Unit, QuadmistSetupData, Unit, Unit] {

  def boot(flags: Map[String, String]): BootResult[Unit] =
    BootResult(GameConfig.default.withViewport(1024, 768).withClearColor(ClearColor.White), ())
      .withAssets(Assets.assets)

  def initialScene(bootData: Unit): Option[SceneName] =
    Some(EmptyScene.name)

  def scenes(bootData: Unit): NonEmptyList[Scene[QuadmistSetupData, Unit, Unit]] =
    NonEmptyList(EmptyScene)

  def setup(bootData: Unit, assetCollection: AssetCollection, dice: Dice): Startup[QuadmistSetupData] = {
    val quadmistWS = WebSocketConfig(
      id = WebSocketId("quadmist"),
      address = s"ws://localhost:8080/join/7919293f-88b9-411e-9920-57bff4c5a8cf"
    )
    Startup.Success(
      QuadmistSetupData(
        quadmistWS = quadmistWS
      )
    ).addGlobalEvents(WebSocketEvent.ConnectOnly(quadmistWS))
  }

  def initialModel(startupData: QuadmistSetupData): Unit = {
    ()
  }

  def initialViewModel(startupData: QuadmistSetupData, model: Unit): Unit =
    ()
}
