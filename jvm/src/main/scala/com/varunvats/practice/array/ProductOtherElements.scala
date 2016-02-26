package com.varunvats.practice.array

import scala.annotation.tailrec

object ProductOtherElements {

  def apply(input: Array[Int]): Array[Int] = {
    if (input.length <= 1)
      return Array(input: _*)
    val (product1, firstZeroIndexO) = process(input, 0, 1)
    firstZeroIndexO.fold(createOutputArray(input, product1)) { firstZeroIndex =>
      val (product, secondZeroIndexO) = process(input, firstZeroIndex + 1, product1)
      secondZeroIndexO.fold(createOutputArray(input.length, firstZeroIndex, product)) { _ =>
        Array.fill(input.length)(0)
      }
    }
  }

  @tailrec
  private def process(arr: Array[Int], index: Int, acc: Int): (Int, Option[Int]) = {
    if (index >= arr.length)
      return (acc, None)
    if (arr(index) == 0)
      return (acc, Some(index))
    process(arr, index + 1, acc * arr(index))
  }

  private def createOutputArray(input: Array[Int], product: Int): Array[Int] = input.map(value => product / value)

  private def createOutputArray(length: Int, zeroIndex: Int, product: Int): Array[Int] = {
    val arr = Array.fill(length)(0)
    arr(zeroIndex) = product
    arr
  }

}
