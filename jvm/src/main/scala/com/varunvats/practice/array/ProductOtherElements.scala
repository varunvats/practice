package com.varunvats.practice.array

import scala.annotation.tailrec

object ProductOtherElements {

  private case class Accum(accum: Int)

  private case class ZIndex(index: Int)

  def apply(input: Array[Int]): Array[Int] = {
    if (input.length <= 1)
      return Array(input: _*)
    val res = process(input, 0, (Accum(1), Option.empty[(Accum, ZIndex)]))
    res match {
      case (Accum(accum), None) if accum != 0 => // No zeroes in array.
        input.map(value => accum / value)
      case (_, None) => // More than one zero in array.
        Array.fill(input.length)(0)
      case (_, Some((Accum(accum), ZIndex(zeroValueIndex)))) => // Only one zero in array.
        val output = Array.fill(input.length)(0)
        output(zeroValueIndex) = accum
        output
    }
  }

  @tailrec
  private def process(input: Array[Int],
                      index: Int,
                      result: (Accum, Option[(Accum, ZIndex)])): (Accum, Option[(Accum, ZIndex)]) = {
    if (index == input.length)
      return result
    val nextIndex = index + 1
    if (input(index) == 0)
      result match {
        case (Accum(accum), None) => // First zero.
          val newResult = (Accum(0), Some(Accum(accum), ZIndex(index)))
          process(input, nextIndex, newResult)
        case (_, Some(_)) => // Second zero.
          (Accum(0), None)
      }
    else
      result match {
        case (Accum(accum), None) => // No zeroes so far.
          val newResult = (Accum(accum * input(index)), None)
          process(input, nextIndex, newResult)
        case (zero, Some((Accum(accum), zeroIndex))) => // One zero so far.
          val newResult = (zero, Some(Accum(accum * input(index)), zeroIndex))
          process(input, nextIndex, newResult)
      }
  }
}
