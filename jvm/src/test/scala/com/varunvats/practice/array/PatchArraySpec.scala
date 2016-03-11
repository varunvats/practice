package com.varunvats.practice.array

import com.varunvats.practice.sorting.UnitSpec

class PatchArraySpec extends UnitSpec {

  val givenTheArray = afterWord("given the array")

  "The list of numbers to patch the array with" when givenTheArray {

    "[1, 2, 3, 4, 5] and an end sum of 5 must be empty" in {
      val nums = Seq(1, 2, 3, 4, 5)
      PatchArray(nums, 5) shouldBe Nil
    }

    "[1, 1, 1, 1, 1] and an end sum of 5 must be empty" in {
      val nums = Seq(1, 1, 1, 1, 1)
      PatchArray(nums, 5) shouldBe Nil
    }

    "[] (empty array) and an end sum of 6 must be [4, 2, 1]" in {
      val nums = Nil
      PatchArray(nums, 6) shouldBe Seq(4, 2, 1)
    }

    "[1001, 999] and an end sum of 7 must be [4, 2, 1]" in {
      val nums = Seq(999, 1001)
      PatchArray(nums, 7) shouldBe Seq(4, 2, 1)
    }

    "[1, 2, 3, 5] and an end sum of 0 must be [] (empty array)" in {
      val nums = Seq(1, 2, 3, 5)
      PatchArray(nums, 0) shouldBe Nil
    }

    "[1, 3] and an end sum of 6 must be [2]" in {
      val nums = Seq(1, 3)
      PatchArray(nums, 6) shouldBe Seq(2)
    }

    "[5, 7] and an end sum of 20 must be [19, 3, 2, 1]" in {
      val nums = Seq(5, 7)
      PatchArray(nums, 20) shouldBe Seq(20, 4, 2, 1)
    }
  }

}
