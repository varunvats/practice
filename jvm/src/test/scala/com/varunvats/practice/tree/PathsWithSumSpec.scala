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

      "only one node and its value is greater than the sum" in {
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
      "a node with the value of sum and no other paths sum to this value (tree contains only +ve numbers)" in {
        val tree = BinaryTreeNode(10,
          left = Some(BinaryTreeNode(7,
            left = Some(BinaryTreeNode(6)),
            right = Some(BinaryTreeNode(9)))),
          right = Some(BinaryTreeNode(12,
            right = Some(BinaryTreeNode(13)))))
        PathsWithSum(tree, 7) shouldBe 1
      }

      "no node with the value of sum and one path that sums to this value (tree has +ve and -ve numbers)" in {
        val tree = BinaryTreeNode(-3,
          left = Some(BinaryTreeNode(-6,
            left = Some(BinaryTreeNode(-7)),
            right = Some(BinaryTreeNode(-4)))),
          right = Some(BinaryTreeNode(-1,
            left = Some(BinaryTreeNode(-1,
              left = Some(BinaryTreeNode(-2)))),
            right = Some(BinaryTreeNode(3,
              left = Some(BinaryTreeNode(2)),
              right = Some(BinaryTreeNode(4)))))))
        PathsWithSum(tree, -9) shouldBe 1
      }
    }

    "be 2" when theTreeContains {
      "two nodes with the value of sum and no other paths sum to this value (tree contains only +ve numbers)" in {
        val tree = BinaryTreeNode(10,
          left = Some(BinaryTreeNode(7,
            left = Some(BinaryTreeNode(7)),
            right = Some(BinaryTreeNode(9)))),
          right = Some(BinaryTreeNode(12,
            right = Some(BinaryTreeNode(13)))))
        PathsWithSum(tree, 7) shouldBe 2
      }

      "one node with the value of sum and one path that sums to this value (tree contains only +ve numbers)" in {
        val tree = BinaryTreeNode(10,
          left = Some(BinaryTreeNode(7,
            left = Some(BinaryTreeNode(6)),
            right = Some(BinaryTreeNode(9)))),
          right = Some(BinaryTreeNode(12,
            right = Some(BinaryTreeNode(13)))))
        PathsWithSum(tree, 13) shouldBe 2
      }

      "no nodes have the given sum but two paths that sum to this value (tree contains +ve and -ve numbers)" in {
        val tree = BinaryTreeNode(-3,
          left = Some(BinaryTreeNode(-6,
            left = Some(BinaryTreeNode(-7)),
            right = Some(BinaryTreeNode(-4)))),
          right = Some(BinaryTreeNode(-1,
            left = Some(BinaryTreeNode(-1,
              left = Some(BinaryTreeNode(-2)))),
            right = Some(BinaryTreeNode(3,
              left = Some(BinaryTreeNode(2)),
              right = Some(BinaryTreeNode(4)))))))
        PathsWithSum(tree, -13) shouldBe 2
      }
    }

    "be 3" when theTreeContains {
      "one node with the value of sum and two paths that sum to this value (tree contains +ve and -ve numbers)" in {
        val tree = BinaryTreeNode(-3,
          left = Some(BinaryTreeNode(-6,
            left = Some(BinaryTreeNode(-7)),
            right = Some(BinaryTreeNode(-4)))),
          right = Some(BinaryTreeNode(-1,
            left = Some(BinaryTreeNode(-1,
              left = Some(BinaryTreeNode(-2)))),
            right = Some(BinaryTreeNode(3,
              left = Some(BinaryTreeNode(2)),
              right = Some(BinaryTreeNode(4)))))))
        PathsWithSum(tree, -4) shouldBe 3
      }
    }
  }

}
