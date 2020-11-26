package com.lambdarat.quadmist

import com.lambdarat.quadmist.Quadmist.Assets
import indigo._
import indigo.scenes._

import scala.scalajs.js.annotation.JSExportTopLevel

@JSExportTopLevel("IndigoGame")
object Quadmist extends IndigoGame[Unit, Unit, Unit, Unit] {

  private val baseUrl = "assets"

  object Assets {
    val cardName = AssetName("card")
    val cardAsset = AssetType.Image(cardName, AssetPath(s"$baseUrl/card.png"))

    val blockName = AssetName("block")
    val blockAsset = AssetType.Image(blockName, AssetPath(s"$baseUrl/block.png"))

    val freeName = AssetName("free")
    val freeAsset = AssetType.Image(freeName, AssetPath(s"$baseUrl/free.png"))

    val assets: Set[AssetType] = Set(cardAsset, blockAsset, freeAsset)
  }

  def boot(flags: Map[String, String]): BootResult[Unit] =
    BootResult(GameConfig.default.withViewport(1024, 768).withClearColor(ClearColor.White), ())
      .withAssets(Assets.assets)

  def initialScene(bootData: Unit): Option[SceneName] =
    Some(EmptyScene.name)

  def scenes(bootData: Unit): NonEmptyList[Scene[Unit, Unit, Unit]] =
    NonEmptyList(EmptyScene)

  def setup(bootData: Unit, assetCollection: AssetCollection, dice: Dice): Startup[Unit] =
    Startup.Success(())

  def initialModel(startupData: Unit): Unit =
    ()

  def initialViewModel(startupData: Unit, model: Unit): Unit =
    ()

}

object EmptyScene extends Scene[Unit, Unit, Unit] {

  type SceneModel     = Unit
  type SceneViewModel = Unit

  val name: SceneName =
    SceneName("empty")

  val modelLens: Lens[Unit, Unit] =
    Lens.keepLatest

  val viewModelLens: Lens[Unit, Unit] =
    Lens.keepLatest

  val eventFilters: EventFilters =
    EventFilters.Default

  val subSystems: Set[SubSystem] =
    Set()

  def updateModel(context: FrameContext[Unit], model: Unit): GlobalEvent => Outcome[Unit] =
    _ => Outcome(model)

  def updateViewModel(
      context: FrameContext[Unit],
      model: Unit,
      viewModel: Unit
  ): GlobalEvent => Outcome[Unit] =
    _ => Outcome(viewModel)

  def present(context: FrameContext[Unit], model: Unit, viewModel: Unit): SceneUpdateFragment = {
    val screenMargin = 128
    val columnWidth = 192
    def cardNextColumn(column: Int): Int = screenMargin + columnWidth * (column - 1)

    val rowHeight = 192
    def cardNextRow(row: Int): Int = rowHeight * (row - 1)

    val hMargin = 26
    val vMargin = 6

    def buildCard: Graphic = Graphic(Rectangle(0, 0, 140, 180), 1, Material.Textured(Assets.freeName))

    def buildRow(rowIdx: Int): List[Graphic] = {
      val square1 = buildCard.moveTo(cardNextColumn(1) + hMargin, cardNextRow(rowIdx) + vMargin)
      val square2 = buildCard.moveTo(cardNextColumn(2) + hMargin, cardNextRow(rowIdx) + vMargin)
      val square3 = buildCard.moveTo(cardNextColumn(3) + hMargin, cardNextRow(rowIdx) + vMargin)
      val square4 = buildCard.moveTo(cardNextColumn(4) + hMargin, cardNextRow(rowIdx) + vMargin)

      List(square1, square2, square3, square4)
    }

    val squares = List(
      buildRow(1),
      buildRow(2),
      buildRow(3),
      buildRow(4)
    ).flatten

    SceneUpdateFragment.empty
      .addGameLayerNodes(squares)
  }

}
