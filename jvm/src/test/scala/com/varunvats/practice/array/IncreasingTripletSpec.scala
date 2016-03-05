package com.varunvats.practice.array

import com.varunvats.practice.sorting.UnitSpec

class IncreasingTripletSpec extends UnitSpec {

  "An increasing triplet" must {

    "not exist" when {
      "the array contains 2 or fewer elements" in {
        val empty = Array.emptyIntArray
        IncreasingTriplet(empty) shouldBe false
        val oneElem = Array(5)
        IncreasingTriplet(oneElem) shouldBe false
        val twoElems = Array(5, 6)
        IncreasingTriplet(twoElems) shouldBe false
      }

      "the array has all the same elements" in {
        val arr = Array.fill(5)(6)
        IncreasingTriplet(arr) shouldBe false
      }

      "the array is sorted in decreasing order" in {
        val arr = Array(7, 6, 5, 4, 3, 2)
        IncreasingTriplet(arr) shouldBe false
      }

      "the array contains only two elements in increasing order" in {
        val arr = Array(5, 4, 7, 2, 3, 1)
        IncreasingTriplet(arr) shouldBe false
      }
    }

    "exist" when {
      "the array is sorted in increasing order" in {
        val arr = Array(5, 6, 7)
        IncreasingTriplet(arr) shouldBe true
      }

      "only one such sequence" in {
        val arr = Array(10, 9, 8, 7, 6, 7, 4, 9, 2, 1, 0)
        IncreasingTriplet(arr) shouldBe true
      }
    }
  }

}
