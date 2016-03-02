package com.varunvats.practice.tree

import com.varunvats.practice.sorting.UnitSpec

class PathsWithSumSpec extends UnitSpec {

  val theTreeContains = afterWord("the tree contains")

  "The number of paths with the given sum" must {
    "be zero" when theTreeContains {
      "only one node and its value is lower than the sum" in {
        val tree = BinaryTreeNode(10)
        PathsWithSum(tree, 16) shouldBe 0
      }

      "only one and its value is greater than the sum" in {
        val tree = BinaryTreeNode(16)
        PathsWithSum(tree, 10) shouldBe 0
      }

      "no path that sums up to the given sum" in {
        val tree = BinaryTreeNode(10,
          left = Some(BinaryTreeNode(7,
            left = Some(BinaryTreeNode(6)),
            right = Some(BinaryTreeNode(9)))),
          right = Some(BinaryTreeNode(12,
            right = Some(BinaryTreeNode(13)))))
        PathsWithSum(tree, 11) shouldBe 0
      }
    }

    "be 1" when theTreeContains {
      "a node with the value of sum and no other paths sum to this value" in pending
    }
  }

}
