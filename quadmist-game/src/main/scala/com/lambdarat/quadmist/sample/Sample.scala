package com.lambdarat.quadmist.sample

import com.lambdarat.quadmist.common.domain.{Card, Player}
import com.lambdarat.quadmist.common.game.GameEvent.PlayerHand
import com.lambdarat.quadmist.common.game.InitialHand
import io.chrisdavenport.fuuid.FUUID.fuuid

object Sample {
  val playerOneId = Player.Id(fuuid("7919293f-88b9-411e-9920-57bff4c5a8cf"))
  val playerTwoId = Player.Id(fuuid("7d588a91-c7a1-4a02-8f78-c3929c119849"))

  val playerOneHand = PlayerHand(
    InitialHand(
      c1 = Card.Id(fuuid("353dc0fe-e3d5-4d09-bcac-951d60865ea2")),
      c2 = Card.Id(fuuid("cd098048-3517-4f39-a25a-4a1e5f0c3e7e")),
      c3 = Card.Id(fuuid("12ed5740-d894-4f92-a0b6-0ae494acbdbc")),
      c4 = Card.Id(fuuid("c88ddb5f-a085-4607-8476-c9eb5618f3f4")),
      c5 = Card.Id(fuuid("b1ab1509-0c2b-404b-a968-2e49b90463d3"))
    )
  )

  val playerTwoHand = PlayerHand(
    InitialHand(
      c1 = Card.Id(fuuid("132cd988-0291-43f2-a846-65498546d09f")),
      c2 = Card.Id(fuuid("19e7ba53-9393-43c2-b5e2-6e814c466783")),
      c3 = Card.Id(fuuid("ed94ad8b-90c3-41fd-91c1-0008f621b5a0")),
      c4 = Card.Id(fuuid("b8239040-638b-4039-ac9a-390fc3019bfd")),
      c5 = Card.Id(fuuid("0774c28e-795c-4078-b0a6-98864a0cf750"))
    )
  )
}
