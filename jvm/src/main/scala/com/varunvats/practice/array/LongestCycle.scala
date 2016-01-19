package com.varunvats.practice.array

import scala.annotation.tailrec

object LongestCycle {

  def get(arr: IndexedSeq[Int]): Seq[Int] = {
    val longestCycle = arr.foldLeft(Seq.empty[Int]) { (longestCycleSoFar, start) =>
      val newCycle = getNewCycle(arr, start, Set.empty)
      if (newCycle.length > longestCycleSoFar.length)
        newCycle
      else
        longestCycleSoFar
    }
    longestCycle.reverse
  }

  @tailrec
  private def getNewCycle(arr: IndexedSeq[Int], start: Int, visited: Set[Int]): Seq[Int] = {
    val newVisited = visited + start
    if (!arr.isDefinedAt(start))
      Nil
    else if (visited.contains(start)) {
      getCycle(arr, start, Seq(start), arr(start))
    } else {
      getNewCycle(arr, arr(start), newVisited)
    }
  }

  @tailrec
  private def getCycle(arr: IndexedSeq[Int], startOfCycle: Int, cycle: Seq[Int], next: Int): Seq[Int] = {
    if (next == startOfCycle)
      cycle
    else
      getCycle(arr, startOfCycle, next +: cycle, arr(next))
  }
}
