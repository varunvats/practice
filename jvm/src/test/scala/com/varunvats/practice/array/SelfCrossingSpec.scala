package com.varunvats.practice.array

import com.varunvats.practice.sorting.UnitSpec

class SelfCrossingSpec extends UnitSpec {

  val theSequenceOfNumbers = afterWord("the sequence of numbers")

  "A sequence of points" must {

    "not be self-crossing" when theSequenceOfNumbers {
      "has fewer than or equal to 3 entries" in {
        val emptyArray = Array.empty[Int]
        SelfCrossing(emptyArray) shouldBe false
        val oneElem = Array(2)
        SelfCrossing(oneElem) shouldBe false
        val twoElems = Array(2, 1)
        SelfCrossing(twoElems) shouldBe false
        val threeElems = Array(3, 1, 2)
        SelfCrossing(threeElems) shouldBe false
      }

      "forms a monotonically expanding spiral" in {
        val seq = Array(1, 1, 2, 2, 3, 3, 4, 4)
        SelfCrossing(seq) shouldBe false
      }

      "forms an expanding spiral that contracts in the last step" in {
        val seq = Array(1, 1, 2, 2, 3, 4, 3)
        SelfCrossing(seq) shouldBe false
      }

      "forms a monotonically contracting spiral" in {
        val seq = Array(4, 4, 3, 3, 2, 2, 1, 1)
        SelfCrossing(seq) shouldBe false
      }
    }

    "be self crossing" when theSequenceOfNumbers {
      "forms a non-monotonically contracting spiral" in {
        val seq = Array(4, 4, 3, 3, 2, 2, 2)
        SelfCrossing(seq) shouldBe true
      }

      "forms a contracting spiral that expands" in {
        val seq = Array(4, 4, 3, 3, 2, 2, 3)
        SelfCrossing(seq) shouldBe true
      }

      "forms a box" in {
        val box1 = Array(1, 1, 2, 1, 1)
        SelfCrossing(box1) shouldBe true
        val box2 = Array(1, 1, 1, 1)
        SelfCrossing(box2) shouldBe true
      }
    }
  }

}
