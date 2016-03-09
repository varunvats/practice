package com.varunvats.practice.array

object MaxSumNonAdjacentElements {

  def apply(numbers: Seq[Int]): Int = {
    if (numbers.isEmpty)
      return 0
    val (_, max) = numbers.foldLeft((0, 0)) { case ((prevToPrevIndexMax, prevIndexMax), num) =>
      require(num >= 0, "All numbers must be greater than or equal to 0")
      val currIndexMax = Math.max(prevIndexMax, num + prevToPrevIndexMax)
      (prevIndexMax, currIndexMax)
    }
    max
  }
}
