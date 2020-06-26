package com.lambdarat.quadmist

import indigo._

import scala.scalajs.js.annotation.JSExportTopLevel

@JSExportTopLevel("IndigoGame")
object Quadmist extends IndigoDemo[Unit, MyStartUpData, MyGameModel, MyViewModel] {

  def boot(flags: Map[String, String]): BootResult[Unit] =
    BootResult.noData(
      defaultGameConfig
        .withClearColor(ClearColor.fromHexString("0xFF00FF"))
    )

  def setup(
      bootData: Unit,
      assetCollection: AssetCollection,
      dice: Dice
  ): Startup[StartupErrors, MyStartUpData] =
    Startup.Success(MyStartUpData())

  def initialModel(startupData: MyStartUpData): MyGameModel =
    MyGameModel()

  def initialViewModel(startupData: MyStartUpData, model: MyGameModel): MyViewModel = MyViewModel()

  def updateModel(context: FrameContext, model: MyGameModel): GlobalEvent => Outcome[MyGameModel] =
    _ => Outcome(model)

  def updateViewModel(
      context: FrameContext,
      model: MyGameModel,
      viewModel: MyViewModel
  ): Outcome[MyViewModel] =
    Outcome(viewModel)

  def present(
      context: FrameContext,
      model: MyGameModel,
      viewModel: MyViewModel
  ): SceneUpdateFragment =
    noRender
}

// What does your game need to start? E.g. Parsing a level description file
final case class MyStartUpData()

// Your game model is anything you like!
final case class MyGameModel()

// Your view model is also ...anything you like!
final case class MyViewModel()
