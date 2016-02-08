package com.varunvats.practice.array

import com.varunvats.practice.array.BinaryTree.Node
import com.varunvats.practice.sorting.UnitSpec

class BinaryTreeSpec extends UnitSpec {

  val given = afterWord("given")

  "The binary tree creator" when given {

    "an empty array" must {
      "return a None" in {
        BinaryTree.create(Array.empty[Int]) shouldBe None
      }
    }

    "an unsorted array" must {
      "complain" in {
        intercept[IllegalArgumentException] {
          BinaryTree.create(Array(5, 3, 73, 61))
        }
      }
    }

    "an array containing one element" must {
      "return a tree containing only that element" in {
        BinaryTree.create(Array(5)) shouldBe Some(Node(5))
      }
    }

    "an array containing two elements in sorted order" must {
      "return the correct tree" in {
        val arr = Array(3, 5)
        val expectedTree = Some(Node(3, None, Some(Node(5))))
        BinaryTree.create(arr) shouldBe expectedTree
      }
    }

    "an array containing three elements in sorted order" must {
      "return the correct tree" in {
        val arr = Array(3, 101, 99019)
        val expectedTree = Some(Node(101, Some(Node(3)), Some(Node(99019))))
        BinaryTree.create(arr) shouldBe expectedTree
      }
    }

    "an array containing six elements in sorted order" must {
      "return the correct tree" in {
        val arr = Array(21, 64, 101, 163, 164, 201)
        val expectedTree = Some(Node(101,
          left = Some(Node(21, right = Some(Node(64)))),
          right = Some(Node(164, left = Some(Node(163)), right = Some(Node(201)))))
        )
        BinaryTree.create(arr) shouldBe expectedTree
      }
    }

    "an array containing seven elements in sorted order" must {
      "return the correct tree" in {
        val arr = Array(21, 64, 101, 163, 164, 201, 263)
        val expectedTree = Some(Node(163,
          left = Some(Node(64, Some(Node(21)), Some(Node(101)))),
          right = Some(Node(201, Some(Node(164)), Some(Node(263)))))
        )
        BinaryTree.create(arr) shouldBe expectedTree
      }
    }
  }
}
