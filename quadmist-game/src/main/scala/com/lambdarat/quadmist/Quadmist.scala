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
  }

  def boot(flags: Map[String, String]): BootResult[Unit] = {
    BootResult(GameConfig.default.withViewport(1024, 768).withClearColor(ClearColor.White), ())
      .withAssets(Assets.cardAsset)
  }

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

    def buildCard: Graphic = Graphic(Rectangle(0, 0, 140, 180), 1, Material.Textured(Assets.cardName))

    // First row
    val card00 = buildCard.moveTo(cardNextColumn(1) + hMargin, cardNextRow(1) + vMargin)
    val card01 = buildCard.moveTo(cardNextColumn(2) + hMargin, cardNextRow(1) + vMargin)
    val card02 = buildCard.moveTo(cardNextColumn(3) + hMargin, cardNextRow(1) + vMargin)
    val card03 = buildCard.moveTo(cardNextColumn(4) + hMargin, cardNextRow(1) + vMargin)

    // Second row
    val card10 = buildCard.moveTo(cardNextColumn(1) + hMargin, cardNextRow(2) + vMargin)
    val card11 = buildCard.moveTo(cardNextColumn(2) + hMargin, cardNextRow(2) + vMargin)
    val card12 = buildCard.moveTo(cardNextColumn(3) + hMargin, cardNextRow(2) + vMargin)
    val card13 = buildCard.moveTo(cardNextColumn(4) + hMargin, cardNextRow(2) + vMargin)

    // Third row
    val card20 = buildCard.moveTo(cardNextColumn(1) + hMargin, cardNextRow(3) + vMargin)
    val card21 = buildCard.moveTo(cardNextColumn(2) + hMargin, cardNextRow(3) + vMargin)
    val card22 = buildCard.moveTo(cardNextColumn(3) + hMargin, cardNextRow(3) + vMargin)
    val card23 = buildCard.moveTo(cardNextColumn(4) + hMargin, cardNextRow(3) + vMargin)

    // Fourth row
    val card30 = buildCard.moveTo(cardNextColumn(1) + hMargin, cardNextRow(4) + vMargin)
    val card31 = buildCard.moveTo(cardNextColumn(2) + hMargin, cardNextRow(4) + vMargin)
    val card32 = buildCard.moveTo(cardNextColumn(3) + hMargin, cardNextRow(4) + vMargin)
    val card33 = buildCard.moveTo(cardNextColumn(4) + hMargin, cardNextRow(4) + vMargin)

    val cards = List(card00, card01, card02, card03, card10, card11, card12, card13, card20, card21, card22, card23, card30, card31, card32, card33)

    SceneUpdateFragment.empty
      .addGameLayerNodes(cards)
  }

}
