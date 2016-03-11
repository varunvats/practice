package com.varunvats.practice.array

import scala.collection.{BitSet, mutable}

object PatchArray {

  def apply(nums: Seq[Int], end: Int): List[Int] = {
    require(nums == nums.sorted, "The input array must be sorted")
    val possibleSums = getPossibleSums(nums, end)
    patch(possibleSums, end)
  }

  private def patch(possibleSums: mutable.BitSet, end: Int): List[Int] = {
    var patchList = List.empty[Int]
    1 to end foreach { num =>
      if (!possibleSums.contains(num)) {
        patchList = num +: patchList
        val newSums = getNewSums(possibleSums, num, end)
        possibleSums ++= newSums
      }
    }
    patchList
  }

  private def getPossibleSums(nums: Seq[Int], end: Int): mutable.BitSet = {
    val possibleSums = mutable.BitSet.empty
    nums.foreach { num =>
      if (num > end)
        return possibleSums
      val newSums = getNewSums(possibleSums, num, end)
      possibleSums ++= newSums
    }
    possibleSums
  }

  private def getNewSums(possibleSums: BitSet, num: Int, end: Int): List[Int] = {
    var newSums = List(num)
    possibleSums.foreach { possibleSum =>
      val nextPossibleSum = possibleSum + num
      if (nextPossibleSum > end)
        return newSums
      newSums = nextPossibleSum +: newSums
    }
    newSums
  }
}
