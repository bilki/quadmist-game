package com.lambdarat.quadmist.scenes

import com.lambdarat.quadmist.assets.Assets
import com.lambdarat.quadmist.common.game.TurnState
import com.lambdarat.quadmist.models.QuadmistSetupData
import indigo._
import indigo.scenes.{Lens, Scene, SceneName}
import indigo.shared.networking.WebSocketId

object EmptyScene extends Scene[QuadmistSetupData, Unit, Unit] {

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

  def updateModel(
      context: FrameContext[QuadmistSetupData],
      model: Unit
  ): GlobalEvent => Outcome[Unit] = {
    case WebSocketEvent.Receive(WebSocketId("quadmist"), message) => {
      import com.lambdarat.quadmist.common.codecs._
      import io.circe.parser._

      println(parse(message).flatMap(_.hcursor.get[TurnState]("turn")))
      Outcome(model)
    }
    case _                                                        => Outcome(model)
  }

  def updateViewModel(
      context: FrameContext[QuadmistSetupData],
      model: Unit,
      viewModel: Unit
  ): GlobalEvent => Outcome[Unit] =
    _ => Outcome(viewModel)

  def present(
      context: FrameContext[QuadmistSetupData],
      model: Unit,
      viewModel: Unit
  ): SceneUpdateFragment = {
    val screenMargin                     = 128
    val columnWidth                      = 192
    def cardNextColumn(column: Int): Int = screenMargin + columnWidth * (column - 1)

    val rowHeight                  = 192
    def cardNextRow(row: Int): Int = rowHeight * (row - 1)

    val hMargin = 26
    val vMargin = 6

    def buildCard: Graphic =
      Graphic(Rectangle(0, 0, 140, 180), 1, Material.Textured(Assets.freeName))

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
