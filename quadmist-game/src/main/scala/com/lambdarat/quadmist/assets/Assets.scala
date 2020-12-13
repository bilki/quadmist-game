package com.lambdarat.quadmist.assets

import indigo.{AssetName, AssetPath, AssetType, FontKey}

object Assets {
  private val baseUrl = "assets"

  val cardName  = AssetName("card")
  val cardAsset = AssetType.Image(cardName, AssetPath(s"$baseUrl/card.png"))

  val blockName  = AssetName("block")
  val blockAsset = AssetType.Image(blockName, AssetPath(s"$baseUrl/block.png"))

  val freeName  = AssetName("free")
  val freeAsset = AssetType.Image(freeName, AssetPath(s"$baseUrl/free.png"))

  val ponderosaImgName  = AssetName("ponderosa_image")
  val ponderosaImgAsset = AssetType.Image(ponderosaImgName, AssetPath(s"$baseUrl/ponderosa.png"))

  val ponderosaJsonName  = AssetName("ponderosa_json")
  val ponderosaJsonAsset = AssetType.Text(ponderosaJsonName, AssetPath(s"$baseUrl/ponderosa.json"))

  val assets: Set[AssetType] =
    Set(cardAsset, blockAsset, freeAsset, ponderosaImgAsset, ponderosaJsonAsset)

  val ponderosaFontKey = FontKey("ponderosa")
}
