package com.varunvats.practice.tree

import com.varunvats.practice.sorting.UnitSpec

class FirstCommonAncestorSpec extends UnitSpec {

  val of = afterWord("of")

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
      val node1Data = 4
      val node2Data = 6
      FirstCommonAncestor(tree, node1Data, node2Data).get shouldBe BinaryTreeNode(5)
    }

    "be the parent of two nodes when the two nodes are direct children of the parent (parent is not root)" in {
      val tree = BinaryTreeNode(5,
        left = Some(BinaryTreeNode(4)),
        right = Some(BinaryTreeNode(7,
          left = Some(BinaryTreeNode(6)),
          right = Some(BinaryTreeNode(8)))))
      val node1Data = 4
      val node2Data = 6
      FirstCommonAncestor(tree, node1Data, node2Data).get shouldBe BinaryTreeNode(5)
    }

    "be identified correctly" when {
      "the nodes are at different heights" in pending
    }
  }
}
