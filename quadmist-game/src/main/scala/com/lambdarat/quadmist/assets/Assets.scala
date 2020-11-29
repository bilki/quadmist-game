package com.lambdarat.quadmist.assets

import indigo.{AssetName, AssetPath, AssetType}

object Assets {
  private val baseUrl = "assets"

  val cardName = AssetName("card")
  val cardAsset = AssetType.Image(cardName, AssetPath(s"$baseUrl/card.png"))

  val blockName = AssetName("block")
  val blockAsset = AssetType.Image(blockName, AssetPath(s"$baseUrl/block.png"))

  val freeName = AssetName("free")
  val freeAsset = AssetType.Image(freeName, AssetPath(s"$baseUrl/free.png"))

  val assets: Set[AssetType] = Set(cardAsset, blockAsset, freeAsset)
}
