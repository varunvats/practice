package com.varunvats.practice.array

import com.varunvats.practice.sorting.UnitSpec

class ArrayHasSequenceSpec extends UnitSpec {

  val theArrayHas = afterWord("the array has")

  "The sequence checker" must {

    "return true" when theArrayHas {
      "no elements" in {
        ArrayHasSequence(Array.emptyIntArray) shouldBe true
      }

      "only one number" in {
        val arr = Array(5)
        ArrayHasSequence(arr) shouldBe true
      }

      "two numbers of the same value" in {
        val arr = Array(5, 5)
        ArrayHasSequence(arr) shouldBe true
      }

      "two consecutive numbers in sorted order" in {
        val arr = Array(5, 6)
        ArrayHasSequence(arr) shouldBe true
      }

      "two consecutive numbers in unsorted order" in {
        val arr = Array(6, 5)
        ArrayHasSequence(arr) shouldBe true
      }

      "two consecutive numbers repeating more than once" in {
        val arr = Array(6, 6, 6, 5, 5)
        ArrayHasSequence(arr) shouldBe true
      }

      "six consecutive numbers that don't repeat in ascending order" in {
        val arr = Array(5, 6, 7, 8, 9, 10)
        ArrayHasSequence(arr) shouldBe true
      }

      "six consecutive numbers that don't repeat in descending order" in {
        val arr = Array(10, 9, 8, 7, 6, 5)
        ArrayHasSequence(arr) shouldBe true
      }

      "six consecutive numbers that don't repeat in random order" in {
        val arr = Array(7, 10, 5, 8, 6, 9)
        ArrayHasSequence(arr) shouldBe true
      }

      "six consecutive numbers that repeat (random order)" in {
        val arr = Array(8, 10, 7, 5, 7, 9, 5, 5, 7, 5, 8, 10, 6)
        ArrayHasSequence(arr) shouldBe true
      }

      "five numbers of the same value" in {
        val arr = Array(6, 6, 6, 6, 6)
        ArrayHasSequence(arr) shouldBe true
      }
    }


    "return false" when theArrayHas {
      "two numbers with a difference of 2 in ascending order" in {
        val arr = Array(5, 7)
        ArrayHasSequence(arr) shouldBe false
      }

      "two numbers with a difference of 2 in descending order" in {
        val arr = Array(7, 5)
        ArrayHasSequence(arr) shouldBe false
      }

      "five non-consecutive numbers that do not repeat in ascending order" in {
        val arr = Array(5, 6, 8, 9, 10)
        ArrayHasSequence(arr) shouldBe false
      }

      "five non-consecutive numbers that do not repeat in descending order" in {
        val arr = Array(10, 9, 8, 6, 5)
        ArrayHasSequence(arr) shouldBe false
      }

      "five non-consecutive numbers that do not repeat in random order" in {
        val arr = Array(9, 7, 10, 5, 8)
        ArrayHasSequence(arr) shouldBe false
      }

      "five non-consecutive numbers that repeat (random order)" in {
        val arr = Array(8, 10, 8, 7, 9, 9, 5, 10, 8, 9)
        ArrayHasSequence(arr) shouldBe false
      }
    }
  }

}
