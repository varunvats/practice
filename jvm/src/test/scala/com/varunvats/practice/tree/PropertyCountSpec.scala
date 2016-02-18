package com.varunvats.practice.tree

import com.varunvats.practice.sorting.UnitSpec

class PropertyCountSpec extends UnitSpec {

  val for_ = afterWord("for")

  "The property counter" must {
    "return 1 for a tree containing only 1 node" in {
      PropertyCount(BinaryTreeNode(6)) shouldBe 1
    }

    "return 2 for a tree containing two nodes, both of which have the same value" in {
      val tree = BinaryTreeNode(6, Some(BinaryTreeNode(6)))
      PropertyCount(tree) shouldBe 2
    }

    "return 1 for a tree containing two nodes and both the nodes have different values" in {
      val tree = BinaryTreeNode(6, Some(BinaryTreeNode(7)))
      PropertyCount(tree) shouldBe 1
    }

    "return 3 for a tree containing 3 nodes that satisfy the property" in {
      val tree = BinaryTreeNode(1,
        left = Some(BinaryTreeNode(1, Some(BinaryTreeNode(1)), Some(BinaryTreeNode(2)))),
        right = Some(BinaryTreeNode(1)))
      PropertyCount(tree) shouldBe 3
    }

    "return 8 for a tree containing 8 nodes that satisfy the property" in {
      val tree = BinaryTreeNode(1,
        left = Some(BinaryTreeNode(2,
          left = Some(BinaryTreeNode(1,
            left = Some(BinaryTreeNode(1)),
            right = Some(BinaryTreeNode(1)))),
          right = Some(BinaryTreeNode(5)))),
        right = Some(BinaryTreeNode(2,
          left = Some(BinaryTreeNode(6,
            left = Some(BinaryTreeNode(6)),
            right = Some(BinaryTreeNode(6)))),
          right = Some(BinaryTreeNode(7)))))
      PropertyCount(tree) shouldBe 8
    }

    "return 6 for a tree containing 6 nodes that all have the same value" in {
      val tree = BinaryTreeNode(1,
        left = Some(BinaryTreeNode(1,
          left = Some(BinaryTreeNode(1)))),
        right = Some(BinaryTreeNode(1,
          left = Some(BinaryTreeNode(1,
            right = Some(BinaryTreeNode(1)))))))
      PropertyCount(tree) shouldBe 6
    }
  }
}
