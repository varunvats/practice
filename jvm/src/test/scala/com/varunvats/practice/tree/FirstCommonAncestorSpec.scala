package com.varunvats.practice.tree

import com.varunvats.practice.sorting.UnitSpec

class FirstCommonAncestorSpec extends UnitSpec {

  "The first common ancestor of two nodes" must {
    "not exist" when {
      "the tree contains only one node" in {
        val tree = BinaryTreeNode(5)
        val node1Data = 5
        val node2Data = 6
        FirstCommonAncestor(tree, node1Data, node2Data) shouldBe None
      }

      "both the nodes are not in the tree" in {
        val tree = BinaryTreeNode(5, left = Some(BinaryTreeNode(4)), right = Some(BinaryTreeNode(6)))
        val node1Data = 2
        val node2Data = 7
        FirstCommonAncestor(tree, node1Data, node2Data) shouldBe None
      }

      "one of the nodes is not in the tree" in {
        val tree = BinaryTreeNode(5, left = Some(BinaryTreeNode(4)), right = Some(BinaryTreeNode(6)))
        val node1Data = 4
        val node2Data = 7
        FirstCommonAncestor(tree, node1Data, node2Data) shouldBe None
      }

      "both the nodes are in the tree, but the tree contains only these two nodes" in {
        val tree = BinaryTreeNode(5, left = Some(BinaryTreeNode(4)))
        val node1Data = 5
        val node2Data = 4
        FirstCommonAncestor(tree, node1Data, node2Data) shouldBe None
      }
    }

    "be the root when the two nodes are the children of the root node" in {
      val tree = BinaryTreeNode(5, left = Some(BinaryTreeNode(4)), right = Some(BinaryTreeNode(6)))
      FirstCommonAncestor(tree, 4, 6).get shouldBe tree
    }

    "be the parent of two nodes when the two nodes are direct children of the parent (parent is not root)" in {
      val tree = BinaryTreeNode(5,
        left = Some(BinaryTreeNode(4)),
        right = Some(BinaryTreeNode(7,
          left = Some(BinaryTreeNode(6)),
          right = Some(BinaryTreeNode(8)))))
      FirstCommonAncestor(tree, 4, 6).get shouldBe tree
    }

    "be identified correctly" when {
      "the nodes are at different heights" in {
        val tree = BinaryTreeNode(9,
          left = Some(BinaryTreeNode(6,
            left = Some(BinaryTreeNode(2)),
            right = Some(BinaryTreeNode(1)))),
          right = Some(BinaryTreeNode(7,
            left = Some(BinaryTreeNode(0,
              right = Some(BinaryTreeNode(9,
                left = Some(BinaryTreeNode(12)))))),
            right = Some(BinaryTreeNode(6,
              right = Some(BinaryTreeNode(10,
                left = Some(BinaryTreeNode(16)),
                right = Some(BinaryTreeNode(12)))))))))
        FirstCommonAncestor(tree, 16, 9).get shouldBe tree.right.get
      }

      "the nodes have the same value" in {
        val tree = BinaryTreeNode(9,
          left = Some(BinaryTreeNode(6,
            left = Some(BinaryTreeNode(2)),
            right = Some(BinaryTreeNode(1)))),
          right = Some(BinaryTreeNode(7,
            left = Some(BinaryTreeNode(0,
              right = Some(BinaryTreeNode(9,
                left = Some(BinaryTreeNode(12)))))),
            right = Some(BinaryTreeNode(6,
              right = Some(BinaryTreeNode(10,
                left = Some(BinaryTreeNode(16)),
                right = Some(BinaryTreeNode(21)))))))))
        FirstCommonAncestor(tree, 10, 10).get shouldBe tree.right.get.right.get
      }

      "one of the nodes is contained in a sub-tree of the other node" in {
        val tree = BinaryTreeNode(9,
          left = Some(BinaryTreeNode(6,
            left = Some(BinaryTreeNode(7)),
            right = Some(BinaryTreeNode(13)))),
          right = Some(BinaryTreeNode(5,
            left = Some(BinaryTreeNode(12,
              left = Some(BinaryTreeNode(31)),
              right = Some(BinaryTreeNode(14,
                left = Some(BinaryTreeNode(71)))))),
            right = Some(BinaryTreeNode(21,
              right = Some(BinaryTreeNode(11,
                left = Some(BinaryTreeNode(19)))))))))
        FirstCommonAncestor(tree, 12, 71).get shouldBe tree.right.get
      }
    }
  }
}
