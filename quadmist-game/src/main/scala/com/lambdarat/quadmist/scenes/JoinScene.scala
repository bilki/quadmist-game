package com.lambdarat.quadmist.scenes

import com.lambdarat.quadmist.assets.Assets
import com.lambdarat.quadmist.common.domain.Board
import com.lambdarat.quadmist.models.QuadmistSetupData
import indigo.Text
import indigo.scenes.{Lens, Scene, SceneName}
import indigo.shared.events.{EventFilters, GlobalEvent}
import indigo.shared.scenegraph.SceneUpdateFragment
import indigo.shared.subsystems.SubSystem
import indigo.shared.{FrameContext, Outcome}

object JoinScene extends Scene[QuadmistSetupData, Board, Unit] {

  override def modelLens: Lens[Board, Board] = Lens.keepLatest

  override def viewModelLens: Lens[Unit, Unit] = Lens.keepLatest

  type SceneModel     = Board
  type SceneViewModel = Unit

  override def name: SceneName = SceneName("join")

  override def eventFilters: EventFilters = EventFilters.Default

  override def subSystems: Set[SubSystem] = Set.empty

  override def updateModel(
      context: FrameContext[QuadmistSetupData],
      model: Board
  ): GlobalEvent => Outcome[Board] = _ => Outcome(model)

  override def updateViewModel(
      context: FrameContext[QuadmistSetupData],
      model: Board,
      viewModel: Unit
  ): GlobalEvent => Outcome[Unit] = _ => Outcome(viewModel)

  override def present(
      context: FrameContext[QuadmistSetupData],
      model: Board,
      viewModel: Unit
  ): SceneUpdateFragment = {
    val welcomeMessage = Text("Welcome to quadmaster", 0, 0, 1, Assets.ponderosaFontKey)
    SceneUpdateFragment.empty.addGameLayerNodes(welcomeMessage)
  }
}
