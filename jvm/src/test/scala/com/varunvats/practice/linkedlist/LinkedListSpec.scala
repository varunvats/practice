package com.varunvats.practice.linkedlist

import com.varunvats.practice.linkedlist.LinkedList.LLNode
import com.varunvats.practice.sorting.UnitSpec

class LinkedListSpec extends UnitSpec {

  val givenAListContaining = afterWord("given a list containing")

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

      "the position is the length of the list minus 1" in {
        val list = LLNode(7, Some(LLNode(5, Some(LLNode(6, Some(LLNode(21)))))))
        val expectedList = LLNode(7, Some(LLNode(5, Some(LLNode(6, Some(LLNode(99, Some(LLNode(21)))))))))
        LinkedList.insert(list, 99, 3) shouldBe expectedList
      }
    }
  }

  "Calling update on a linked-list" must {

    "throw an exception" when {
      "the position to update is less than 0" in {
        intercept[IndexOutOfBoundsException] {
          LinkedList.update(LLNode(5), 6, -1)
        }
      }

      "the position to insert is equal to the length of the list" in {
        intercept[IndexOutOfBoundsException] {
          LinkedList.update(LLNode(5), 6, 1)
        }
        intercept[IndexOutOfBoundsException] {
          LinkedList.update(LLNode(5, Some(LLNode(21))), 6, 2)
        }
      }

      "the position to insert is greater than the length of the list" in {
        intercept[IndexOutOfBoundsException] {
          LinkedList.update(LLNode(5), 6, 2)
        }
        intercept[IndexOutOfBoundsException] {
          LinkedList.update(LLNode(5, Some(LLNode(21))), 6, 5)
        }
      }
    }

    "update the data at the given position" when {
      "the position is the head of the list and the list contains only one element" in {
        val list = LLNode(5)
        LinkedList.update(list, 6, 0)
        list shouldBe LLNode(6)
      }

      "the position is the head of the list and the list contains more than one element" in {
        val list = LLNode(5, Some(LLNode(7, Some(LLNode(2, Some(LLNode(33)))))))
        LinkedList.update(list, 6, 0)
        list shouldBe LLNode(6, Some(LLNode(7, Some(LLNode(2, Some(LLNode(33)))))))
      }

      "the position is in the middle of the list" in {
        val list = LLNode(5, Some(LLNode(7, Some(LLNode(2, Some(LLNode(33)))))))
        LinkedList.update(list, 6, 2)
        list shouldBe LLNode(5, Some(LLNode(7, Some(LLNode(6, Some(LLNode(33)))))))
      }

      "the position points to the last element in the list" in {
        val list = LLNode(5, Some(LLNode(7, Some(LLNode(2, Some(LLNode(33)))))))
        LinkedList.update(list, 6, 3)
        list shouldBe LLNode(5, Some(LLNode(7, Some(LLNode(2, Some(LLNode(6)))))))
      }
    }
  }

  "Calling removeDuplicates" must {
    "do nothing" when {
      "given a list that doesn't contain duplicates" in {
        val list = LLNode(5, Some(LLNode(4, Some(LLNode(21, Some(LLNode(3)))))))
        LinkedList.removeDuplicates(list)
        list shouldBe LLNode(5, Some(LLNode(4, Some(LLNode(21, Some(LLNode(3)))))))
      }
    }

    "remove duplicates from the list" when {
      "given a list whose first element is a duplicate" in {
        val list = LLNode(5, Some(LLNode(4, Some(LLNode(21, Some(LLNode(3, Some(LLNode(5, Some(LLNode(23)))))))))))
        LinkedList.removeDuplicates(list)
        list shouldBe LLNode(5, Some(LLNode(4, Some(LLNode(21, Some(LLNode(3, Some(LLNode(23)))))))))
      }

      "given a list whose last element is a duplicate" in {
        val list = LLNode(5, Some(LLNode(4, Some(LLNode(21, Some(LLNode(3, Some(LLNode(1, Some(LLNode(21)))))))))))
        LinkedList.removeDuplicates(list)
        list shouldBe LLNode(5, Some(LLNode(4, Some(LLNode(21, Some(LLNode(3, Some(LLNode(1)))))))))
      }

      "given a list whose last two elements are duplicates" in {
        val list = LLNode(5, Some(LLNode(4, Some(LLNode(21, Some(LLNode(3, Some(LLNode(3, Some(LLNode(21)))))))))))
        LinkedList.removeDuplicates(list)
        list shouldBe LLNode(5, Some(LLNode(4, Some(LLNode(21, Some(LLNode(3)))))))
      }
    }
  }

  "The 0th last element" when givenAListContaining {
    "only one element must be None" in {
      val list = LLNode(5)
      LinkedList.lastRecursive(list, 0) shouldBe None
      LinkedList.lastIterative(list, 0) shouldBe None
    }

    "three element must be None" in {
      val list = LLNode(5, Some(LLNode(7, Some(LLNode(3)))))
      LinkedList.lastRecursive(list, 0) shouldBe None
      LinkedList.lastIterative(list, 0) shouldBe None
    }
  }

  "The 1st last element" when givenAListContaining {
    "only one element must be the only element in the list" in {
      val list = LLNode(5)
      LinkedList.lastRecursive(list, 1).get shouldBe list
      LinkedList.lastIterative(list, 1).get shouldBe list
    }

    "three elements must be the last element in the list" in {
      val list = LLNode(5, Some(LLNode(7, Some(LLNode(3)))))
      val last = list.next.get.next.get
      LinkedList.lastRecursive(list, 1).get shouldBe last
      LinkedList.lastIterative(list, 1).get shouldBe last
    }
  }

  "The 2nd last element" when givenAListContaining {
    "only one element must be None" in {
      val list = LLNode(5)
      LinkedList.lastRecursive(list, 2) shouldBe None
      LinkedList.lastIterative(list, 2) shouldBe None
    }

    "two elements must be the first element of the list" in {
      val list = LLNode(5, Some(LLNode(6)))
      LinkedList.lastRecursive(list, 2).get shouldBe list
      LinkedList.lastIterative(list, 2).get shouldBe list
    }

    "three elements must be the second element of the list" in {
      val list = LLNode(5, Some(LLNode(6, Some(LLNode(3)))))
      val secondLast = list.next.get
      LinkedList.lastRecursive(list, 2).get shouldBe secondLast
      LinkedList.lastIterative(list, 2).get shouldBe secondLast
    }

    "four elements must be the third element of the list" in {
      val list = LLNode(5, Some(LLNode(6, Some(LLNode(3, Some(LLNode(7)))))))
      val secondLast = list.next.get.next.get
      LinkedList.lastRecursive(list, 2).get shouldBe secondLast
      LinkedList.lastIterative(list, 2).get shouldBe secondLast
    }
  }

  "The 5th last element" when givenAListContaining {
    "four elements must be None" in {
      val list = LLNode(5, Some(LLNode(6, Some(LLNode(3, Some(LLNode(7)))))))
      LinkedList.lastRecursive(list, 5) shouldBe None
      LinkedList.lastIterative(list, 5) shouldBe None
    }
  }
}
