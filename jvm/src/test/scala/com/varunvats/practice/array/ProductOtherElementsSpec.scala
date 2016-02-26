package com.varunvats.practice.array

import com.varunvats.practice.sorting.UnitSpec

class ProductOtherElementsSpec extends UnitSpec {

  val theArrayContains = afterWord("the array contains")

  "Product of other elements calculator" must {

    "return an empty array" when {
      "given an empty array" in {
        ProductOtherElements(Array.emptyIntArray) shouldBe Array.emptyIntArray
      }
    }

    "return an array of zeroes of the same length as the input array" when theArrayContains {
      "only one element and that element is zero" in {
        ProductOtherElements(Array(0)) shouldBe Array(0)
      }

      "two elements and both are zeroes" in {
        ProductOtherElements(Array(0, 0)) shouldBe Array(0, 0)
      }

      "3 elements and two of them are zeroes" in {
        ProductOtherElements(Array(5, 0, 0)) shouldBe Array(0, 0, 0)
      }

      "5 elements and two of them are zeroes" in {
        ProductOtherElements(Array(0, 5, 6, 0, 9)) shouldBe Array(0, 0, 0, 0, 0)
      }

      "5 elements and all of them are zeroes" in {
        ProductOtherElements(Array(0, 0, 0, 0, 0)) shouldBe Array(0, 0, 0, 0, 0)
      }
    }

    "return the input array" when theArrayContains {
      "only one element and that element is not zero" in {
        ProductOtherElements(Array(5)) shouldBe Array(5)
      }
    }

    "return an array containing the correct values" when theArrayContains {
      "two elements and the second element is zero" in {
        ProductOtherElements(Array(5, 0)) shouldBe Array(0, 5)
      }

      "two elements and the first element is zero" in {
        ProductOtherElements(Array(0, 6)) shouldBe Array(6, 0)
      }

      "five elements and only one of them is zero" in {
        ProductOtherElements(Array(5, 9, 0, 3, 1)) shouldBe Array(0, 0, 135, 0, 0)
      }

      "two elements and none of them are zero" in {
        ProductOtherElements(Array(5, 3)) shouldBe Array(3, 5)
      }

      "none of the elements in the array are zero" in {
        ProductOtherElements(Array(5, 7, 3, 2)) shouldBe Array(42, 30, 70, 105)
      }
    }
  }
}
