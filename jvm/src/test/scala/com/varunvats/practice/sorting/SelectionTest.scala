package com.varunvats.practice.sorting

import org.scalatest.{Matchers, WordSpec}

class SelectionTest extends WordSpec with Matchers {

  "Selection sort" must {

    "sort an empty array" in {
      val a = array()
      Selection.sort(a)
      a shouldBe Array.empty[Double]
    }

    "sort an array containing only one element" in {
      val a = array(5.3)
      Selection.sort(a)
      a shouldBe Array(5.3)
    }

    "sort an already sorted array" in {
      val a = array(3, 9, 10.1, 21, 99, 1000.01)
      Selection.sort(a)
      a shouldBe Array(3, 9, 10.1, 21, 99, 1000.01)
    }

    "sort an array sorted in reverse order" in {
      val a = array(100.99, 53, 21, 9.3, 2.1, 0.0)
      Selection.sort(a)
      a shouldBe Array(0.0, 2.1, 9.3, 21, 53, 100.99)
    }

    "sort an unsorted array" in {
      val a = array(5, 9, 10, 3, 0, 29)
      Selection.sort(a)
      a shouldBe Array(0, 3, 5, 9, 10, 29)
    }
  }

  private def array(numbers: Double*) = Array(numbers: _*).map(Double.box)
}
