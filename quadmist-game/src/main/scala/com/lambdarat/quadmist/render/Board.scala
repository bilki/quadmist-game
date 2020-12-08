package com.lambdarat.quadmist.render

import com.lambdarat.quadmist.assets.Assets
import indigo.{Graphic, Material, Rectangle}

object Board {

  def renderFreeBoard: List[Graphic] = {
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

    List(
      buildRow(1),
      buildRow(2),
      buildRow(3),
      buildRow(4)
    ).flatten
  }
}
