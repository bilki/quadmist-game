package com.lambdarat.quadmist.assets

import indigo.{AssetCollection, FontChar, FontInfo, Material}
import indigo.json.Json

object Fonts {
  def makeFontInfo(unknownChar: FontChar, fontChars: List[FontChar]): FontInfo =
    FontInfo(
      fontKey = Assets.yosterFontKey,
      material = Material.Textured(Assets.yosterImgName),
      sheetWidth = 181,
      sheetHeight = 145,
      unknownChar = unknownChar
    ).addChars(fontChars)

  def buildFont(assetCollection: AssetCollection): Option[FontInfo] =
    for {
      json        <- assetCollection.findTextDataByName(Assets.yosterJsonName)
      chars       <- Json.readFontToolJson(json)
      unknownChar <- chars.find(_.character == "#")
    } yield makeFontInfo(unknownChar, chars)
}
