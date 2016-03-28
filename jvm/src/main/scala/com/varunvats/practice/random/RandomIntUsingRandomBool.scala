package com.varunvats.practice.random

import scala.annotation.tailrec
import scala.util.Random

object RandomIntUsingRandomBool {

  def apply(bound: Int): Int = {
    require(bound > 0, "Bound must be positive.")
    randomInt(0, bound)
  }

  @tailrec
  private def randomInt(min: Int, max: Int): Int = {
    if (min == max)
      return min
    val mid = (min + max) / 2
    if (Random.nextBoolean)
      randomInt(min, mid)
    else
      randomInt(mid + 1, max)
  }
}
