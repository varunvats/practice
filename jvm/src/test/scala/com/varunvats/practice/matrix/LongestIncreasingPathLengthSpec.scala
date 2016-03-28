package com.varunvats.practice.matrix

import com.varunvats.practice.sorting.UnitSpec

class LongestIncreasingPathLengthSpec extends UnitSpec {

  "The length of the longest increasing path" when {

    "given an empty matrix must be 0" in {
      LongestIncreasingPathLength(Array.empty[Array[Int]]) shouldBe 0
      LongestIncreasingPathLength(Array(Array.emptyIntArray)) shouldBe 0
    }

    "given a 1x1 matrix must be 1" in {
      LongestIncreasingPathLength(Array(Array(5))) shouldBe 1
    }

    "given a 1x2 matrix with all the same elements must be 1" in {
      pending
    }
  }

}
