package com.varunvats.practice.linkedlist

import com.varunvats.practice.linkedlist.LinkedList.LLNode
import com.varunvats.practice.sorting.UnitSpec

class OddEvenSpec extends UnitSpec {

  "In-place odd-even grouper" must {

    "do nothing" when {
      "the list contains only one element" in {
        val list = LLNode(5)
        OddEven(list)
        list shouldBe LLNode(5)
      }

      "the list contains two elements" in {
        val list = LLNode(5, Some(LLNode(7)))
        OddEven(list)
        list shouldBe LLNode(5, Some(LLNode(7)))
      }
    }

    "group the odd-even nodes correctly" when {
      "the list contains three elements" in {
        val list = LLNode(5, Some(LLNode(3, Some(LLNode(-1)))))
        OddEven(list)
        list shouldBe LLNode(5, Some(LLNode(-1, Some(LLNode(3)))))
      }

      "the list contains four elements" in {
        val list = LLNode(0, Some(LLNode(2, Some(LLNode(3, Some(LLNode(4)))))))
        OddEven(list)
        list shouldBe LLNode(0, Some(LLNode(3, Some(LLNode(2, Some(LLNode(4)))))))
      }

      "the list contains five elements" in {
        val list = LLNode(0, Some(LLNode(2, Some(LLNode(3, Some(LLNode(4, Some(LLNode(6)))))))))
        OddEven(list)
        list shouldBe LLNode(0, Some(LLNode(3, Some(LLNode(6, Some(LLNode(2, Some(LLNode(4)))))))))
      }

      "the list contains six elements" in {
        val list = LLNode(0, Some(LLNode(2, Some(LLNode(3,
          Some(LLNode(4, Some(LLNode(6, Some(LLNode(7)))))))))))
        OddEven(list)
        list shouldBe LLNode(0, Some(LLNode(3, Some(LLNode(6,
          Some(LLNode(2, Some(LLNode(4, Some(LLNode(7)))))))))))
      }
    }
  }

}
