package com.varunvats.practice.linkedlist

import com.varunvats.practice.linkedlist.LinkedList.LLNode
import com.varunvats.practice.sorting.UnitSpec

class LinkedListSpec extends UnitSpec {

  "Calling insert on a linked-list" must {

    "throw an exception" when {
      "the position to insert at is less than 0" in {
        intercept[IndexOutOfBoundsException] {
          LinkedList.insert(LLNode(5), 6, -1)
        }
      }

      "the position to insert is 1 greater than the length of the list" in {
        val head = LLNode(5)
        intercept[IndexOutOfBoundsException] {
          LinkedList.insert(head, 7, 2)
        }
      }

      "the position to insert is 2 greater than the length of the list" in {
        val head = LLNode(5)
        intercept[IndexOutOfBoundsException] {
          LinkedList.insert(head, 7, 3)
        }
      }
    }

    "return a new list with the node inserted" when {
      "the position is 0 and the list contains only one node" in {
        val list = LLNode(5)
        val expectedList = LLNode(7, Some(LLNode(5)))
        LinkedList.insert(list, 7, 0) shouldBe expectedList
      }

      "the position is 0 and the list contains more than one node" in {
        val list = LLNode(5, Some(LLNode(6)))
        val expectedList = LLNode(7, Some(LLNode(5, Some(LLNode(6)))))
        LinkedList.insert(list, 7, 0) shouldBe expectedList
      }

      "the position is 1 and the list contains only one node" in {
        val list = LLNode(5)
        val expectedList = LLNode(5, Some(LLNode(7)))
        LinkedList.insert(list, 7, 1) shouldBe expectedList
      }

      "the position is 1 and the list contains more than one node" in {
        val list = LLNode(7, Some(LLNode(5, Some(LLNode(6, Some(LLNode(21)))))))
        val expectedList = LLNode(7, Some(LLNode(99, Some(LLNode(5, Some(LLNode(6, Some(LLNode(21)))))))))
        LinkedList.insert(list, 99, 1) shouldBe expectedList
      }

      "the position is in the middle of the list" in {
        val list = LLNode(7, Some(LLNode(5, Some(LLNode(6, Some(LLNode(21)))))))
        val expectedList = LLNode(7, Some(LLNode(5, Some(LLNode(99, Some(LLNode(6, Some(LLNode(21)))))))))
        LinkedList.insert(list, 99, 2) shouldBe expectedList
      }

      "the position is at the end of the list" in {
        val list = LLNode(7, Some(LLNode(5, Some(LLNode(6, Some(LLNode(21)))))))
        val expectedList = LLNode(7, Some(LLNode(5, Some(LLNode(6, Some(LLNode(21, Some(LLNode(99)))))))))
        LinkedList.insert(list, 99, 4) shouldBe expectedList
      }
    }
  }

}
