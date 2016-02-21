package com.varunvats.practice.tree

import com.varunvats.practice.sorting.UnitSpec

class InOrderSuccessorSpec extends UnitSpec {

  "The in-order successor getter" must {

    "return None" when {
      "asked for the successor of the root and the tree contains only one node" in {
        val tree = BinaryTreeNodeP(5)
        InOrderSuccessor(tree) shouldBe None
      }

      "asked for the successor of the root and the root has only a left child" in {
        val tree = Some(BinaryTreeNodeP(5,
          left = Some(BinaryTreeNodeP(4))))
        tree.get.left.get.parent = tree
        InOrderSuccessor(tree.get) shouldBe None
      }

      "the node is the largest node in the tree" in {
        val tree = Some(BinaryTreeNodeP(5,
          left = Some(BinaryTreeNodeP(4)),
          right = Some(BinaryTreeNodeP(6))))
        tree.get.left.get.parent = tree
        tree.get.right.get.parent = tree
        InOrderSuccessor(tree.get.right.get) shouldBe None
      }
    }

    "return the parent" when {
      "the node is the left child of its parent" in {
        pending
      }
    }

    "return the next largest ancestor" when {
      "the node is the right child of its parent" in {
        pending
      }
    }
  }
}
