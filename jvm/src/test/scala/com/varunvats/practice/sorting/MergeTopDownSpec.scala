package com.varunvats.practice.sorting

class MergeTopDownSpec extends UnitSpec {

  "Top-down merge-sort" must {

    "sort an empty array" in {
      val a = array()
      MergeTopDown.sort(a)
      a shouldBe Array.empty[Double]
    }

    "sort an array containing only one element" in {
      val a = array(5.3)
      MergeTopDown.sort(a)
      a shouldBe Array(5.3)
    }

    "sort an array containing two elements" in {
      val a = array(9.1, 3.5)
      MergeTopDown.sort(a)
      a shouldBe Array(3.5, 9.1)
    }

    "sort an already sorted array" in {
      val a = array(3, 9, 10.1, 21, 99, 1000.01)
      MergeTopDown.sort(a)
      a shouldBe Array(3, 9, 10.1, 21, 99, 1000.01)
    }

    "sort an array sorted in reverse order" in {
      val a = array(100.99, 53, 21, 9.3, 2.1, 0.0)
      MergeTopDown.sort(a)
      a shouldBe Array(0.0, 2.1, 9.3, 21, 53, 100.99)
    }

    "sort an unsorted array containing even number of elements" in {
      val a = array(5, 10, 9, 3, 0, 29)
      MergeTopDown.sort(a)
      a shouldBe Array(0, 3, 5, 9, 10, 29)
    }

    "sort an unsorted array containing odd number of elements" in {
      val a = array(5, 10, 9, 3, 0, 29, 1)
      MergeTopDown.sort(a)
      a shouldBe Array(0, 1, 3, 5, 9, 10, 29)
    }

    "sort an array in which all the elements have the same value" in {
      val a = array(5, 5, 5, 5, 5)
      MergeTopDown.sort(a)
      a shouldBe Array(5, 5, 5, 5, 5)
    }
  }
}
