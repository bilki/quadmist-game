package com.lambdarat.quadmist.assets

import indigo.{AssetCollection, FontChar, FontInfo, Material, Point}
import indigo.json.Json
import indigo.shared.datatypes.FontSpriteSheet

object Fonts {
  def makeFontInfo(unknownChar: FontChar, fontChars: List[FontChar]): FontInfo = {
    val font = FontInfo(
      fontKey = Assets.ponderosaFontKey,
      fontSpriteSheet =
        FontSpriteSheet(Material.Textured(Assets.ponderosaImgName), Point(180, 162)),
      unknownChar = unknownChar,
      fontChars = fontChars,
      caseSensitive = true
    )

    font.addChar(FontChar(" ", 180, 162, 13, 25))
  }

  def buildFont(assetCollection: AssetCollection): Option[FontInfo] =
    for {
      json        <- assetCollection.findTextDataByName(Assets.ponderosaJsonName)
      chars       <- Json.readFontToolJson(json)
      unknownChar <- chars.find(_.character == "#")
    } yield makeFontInfo(unknownChar, chars)
}
