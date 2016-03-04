package com.varunvats.practice.array

import scala.annotation.tailrec

object SelfCrossing {

  def apply(moves: Seq[Int]): Boolean = {
    if (moves.length <= 3)
      return false
    val initialSpiralType = getSpiralType(moves.head, moves(2))
    isSelfCrossing(moves, initialSpiralType, 3)
  }

  @tailrec
  private def isSelfCrossing(moves: Seq[Int], spiralTypeSoFar: SpiralType, index: Int): Boolean = {
    if (index >= moves.length)
      return false
    val nextSpiralType = getSpiralType(moves(index - 2), moves(index))
    (spiralTypeSoFar, nextSpiralType) match {
      case (Expanding, Expanding) | (Contracting, Contracting) =>
        isSelfCrossing(moves, nextSpiralType, index + 1)
      case (Unchanging, Contracting) =>
        isSelfCrossing(moves, nextSpiralType, index + 1)
      case (Expanding, _) if index == moves.length - 1 =>
        false
      case _ => true
    }
  }

  private def getSpiralType(distance1: Int, distance3: Int): SpiralType =
    if (distance3 < distance1)
      Contracting
    else if (distance3 == distance1)
      Unchanging
    else
      Expanding

  private sealed trait SpiralType

  private object Expanding extends SpiralType

  private object Contracting extends SpiralType

  private object Unchanging extends SpiralType

}
