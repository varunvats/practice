package com.varunvats.practice.array

import com.varunvats.practice.sorting.UnitSpec

class LongestCycleSpec extends UnitSpec {

  val theArrayContains = afterWord("the array contains")

  "The longest cycle" when theArrayContains {

    "no elements" should {
      "be empty" in {
        LongestCycle.get(IndexedSeq.empty) shouldBe Nil
      }
    }

    "one element that does not point to itself" should {
      "be empty" in {
        LongestCycle.get(IndexedSeq(101)) shouldBe Nil
      }
    }

    "one element that points to itself" should {
      "contain only that element" in {
        LongestCycle.get(IndexedSeq(0)) shouldBe Seq(0)
      }
    }

    "two elements that don't form a cycle" should {
      "be empty" in {
        LongestCycle.get(IndexedSeq(1, 2)) shouldBe Nil
      }
    }

    "two elements that both are in one cycle" should {
      "contain both the elements" in {
        LongestCycle.get(IndexedSeq(1, 0)) shouldBe Seq(1, 0)
      }
    }

    "three elements that are all part of the cycle (cycle direction is anti-clockwise in array)" should {
      "contain all the three elements in the correct order" in {
        val arr = IndexedSeq(2, 0, 1)
        LongestCycle.get(arr) shouldBe Seq(2, 1, 0)
      }
    }

    "three elements that are all part of the cycle (cycle direction is clockwise in array)" should {
      "contain all the three elements in the correct order" in {
        val arr = IndexedSeq(1, 2, 0)
        LongestCycle.get(arr) shouldBe Seq(1, 2, 0)
      }
    }

    "four elements that are all part of the cycle (cycle direction is random in array)" should {
      "contain all the four elements in the correct order" in {
        val arr = IndexedSeq(2, 3, 1, 0)
        LongestCycle.get(arr) shouldBe Seq(2, 1, 3, 0)
      }
    }

    "a cycle that starts in the middle of the array" should {
      "contain all the elements in the cycle in the correct order" in {
        val arr = IndexedSeq(99, 0, 4, 2, 3, 21, 63)
        LongestCycle.get(arr) shouldBe Seq(4, 3, 2)
      }
    }

    "two cycles of the same length" should {
      "be the one encountered first from the left" in {
        val arr = IndexedSeq(2, 6, 11, 10, 1, 0, 3, 8, 7, 5, 4, 9)
        val expectedCycle = Seq(2, 11, 9, 5, 0)
        LongestCycle.get(arr) shouldBe expectedCycle
      }
    }

    "two cycles of different lengths" should {
      "the one with more elements" in {
        val arr = IndexedSeq(99, 6, 11, 10, 1, 2, 3, 8, 7, 5, 4, 9)
        val expectedCycle = Seq(6, 3, 10, 4, 1)
        LongestCycle.get(arr) shouldBe expectedCycle
      }
    }

    "a cycle that has more than one element that points to an element in the cycle" should {
      "contain only the elements from the cycle in the correct order" in {
        val arr = IndexedSeq(5, 9, 11, 2, 2, 4, 7, 2, 11, 7, 3, 9)
        val expectedCycle = Seq(2, 11, 9, 7)
        LongestCycle.get(arr) shouldBe expectedCycle
      }
    }
  }
}
