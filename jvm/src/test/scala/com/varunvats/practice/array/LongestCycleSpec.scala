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

    "two elements that form a cycle" should {
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

    "four elements that are all part of the cycle (cycle direction is random in array)" should {
      "contain all the four elements in the correct order" in {
        val arr = IndexedSeq(2, 3, 1, 0)
        LongestCycle.get(arr) shouldBe Seq(2, 1, 3, 0)
      }
    }
  }
}
