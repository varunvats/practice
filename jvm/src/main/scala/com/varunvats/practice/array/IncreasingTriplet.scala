package com.varunvats.practice.array

import scala.annotation.tailrec

object IncreasingTriplet {

  def apply(arr: Seq[Int]): Boolean = increasingTriplet(arr, 0)

  @tailrec
  private def increasingTriplet(arr: Seq[Int], index: Int): Boolean = {
    if (index >= arr.length)
      return false
    val num = arr(index)
    increasingDouble(arr, index + 1, num) || increasingTriplet(arr, index + 1)
  }


  @tailrec
  private def increasingDouble(arr: Seq[Int], index: Int, num: Int): Boolean = {
    if (index >= arr.length)
      return false
    if (arr(index) > num)
      if (arr.indexWhere(_ > arr(index), index + 1) > -1)
        return true
    increasingDouble(arr, index + 1, num)
  }

}
