package com.varunvats.practice.array

import com.varunvats.practice.sorting.UnitSpec

class MaxSumNonAdjacentElementsSpec extends UnitSpec {

  "The max sum of non-adjacent elements of an array" must {

    "be 0 for an empty array" in {
      MaxSumNonAdjacentElements(Array.emptyIntArray) shouldBe 0
    }

    "be 3 for the array [3]" in {
      val arr = Array(3)
      MaxSumNonAdjacentElements(arr) shouldBe 3
    }

    "be 1 for the array [0, 1, 0, 0, 0]" in {
      val arr = Array(0, 1, 0, 0, 0)
      MaxSumNonAdjacentElements(arr) shouldBe 1
    }

    "be 3 for the array [0, 1, 0, 2, 1]" in {
      val arr = Array(0, 1, 0, 2, 1)
      MaxSumNonAdjacentElements(arr) shouldBe 3
    }

    "be 15 for the array [6, 3, 0, 7, 1, 2]" in {
      val arr = Array(6, 3, 0, 7, 1, 2)
      MaxSumNonAdjacentElements(arr) shouldBe 15
    }

    "be 27 for the array [6, 3, 0, 7, 21, 2]" in {
      val arr = Array(6, 3, 0, 7, 21, 2)
      MaxSumNonAdjacentElements(arr) shouldBe 27
    }
  }

}
