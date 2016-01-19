package com.varunvats.practice.array

import scala.annotation.tailrec

object LongestCycle {

  def get(arr: IndexedSeq[Int]): Seq[Int] = {
    val (longestCycle, _) = arr.foldLeft((Seq.empty[Int], Set.empty[Int])) { case ((longestCycleSoFar, visited), start) =>
      if (visited.contains(start))
        (longestCycleSoFar, visited)
      else {
        val (newCycle, newVisited) = getNewCycle(arr, start, Nil, visited)
        if (newCycle.length > longestCycleSoFar.length)
          (newCycle, newVisited)
        else
          (longestCycleSoFar, newVisited)
      }
    }
    longestCycle.reverse
  }

  @tailrec
  private def getNewCycle(arr: IndexedSeq[Int], start: Int, pathSoFar: Seq[Int], visited: Set[Int]): (Seq[Int], Set[Int]) = {
    val newVisited = visited + start
    if (!arr.isDefinedAt(start))
      (Nil, newVisited)
    else if (pathSoFar.contains(start)) {
      val cycle = getCycle(arr, start, Seq(start), arr(start))
      (cycle, newVisited)
    } else {
      getNewCycle(arr, arr(start), start +: pathSoFar, newVisited)
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
