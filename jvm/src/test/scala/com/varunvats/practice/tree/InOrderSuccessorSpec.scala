package com.varunvats.practice.tree

import com.varunvats.practice.sorting.UnitSpec

class InOrderSuccessorSpec extends UnitSpec {

  "The in-order successor getter" must {

    "return None" when {
      "the node is the root and the root has no children" in {
        val tree = BinaryTreeNodeP(5)
        InOrderSuccessor(tree) shouldBe None
      }

      "the node is the root and root has only a left child" in {
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

    "return the right child" when {
      "the node has a right child" in {
        val tree = Some(BinaryTreeNodeP(8,
          left = Some(BinaryTreeNodeP(4,
            left = Some(BinaryTreeNodeP(2,
              right = Some(BinaryTreeNodeP(3)))),
            right = Some(BinaryTreeNodeP(5,
              right = Some(BinaryTreeNodeP(6)))))),
          right = Some(BinaryTreeNodeP(10))))
        tree.get.left.get.parent = tree
        tree.get.right.get.parent = tree
        tree.get.left.get.left.get.parent = tree.get.left
        tree.get.left.get.right.get.parent = tree.get.left
        tree.get.left.get.left.get.right.get.parent = tree.get.left.get.left
        tree.get.left.get.right.get.right.get.parent = tree.get.left.get.right
        InOrderSuccessor(tree.get.left.get) shouldBe tree.get.left.get.right
      }
    }

    "return the parent" when {
      "the node is the left child of its parent and the node doesn't have a right child" in {
        val tree = Some(BinaryTreeNodeP(5,
          left = Some(BinaryTreeNodeP(4,
            left = Some(BinaryTreeNodeP(3)))),
          right = Some(BinaryTreeNodeP(6))))
        tree.get.left.get.parent = tree
        tree.get.right.get.parent = tree
        tree.get.left.get.left.get.parent = tree.get.left
        InOrderSuccessor(tree.get.left.get.left.get) shouldBe tree.get.left
      }
    }

    "return the next largest ancestor" when {
      "the node is the right child of its parent and it doesn't have a right child" in {
        val tree = Some(BinaryTreeNodeP(8,
          left = Some(BinaryTreeNodeP(4,
            left = Some(BinaryTreeNodeP(2,
              right = Some(BinaryTreeNodeP(3)))),
            right = Some(BinaryTreeNodeP(5,
              right = Some(BinaryTreeNodeP(6)))))),
          right = Some(BinaryTreeNodeP(10))))
        tree.get.left.get.parent = tree
        tree.get.right.get.parent = tree
        tree.get.left.get.left.get.parent = tree.get.left
        tree.get.left.get.right.get.parent = tree.get.left
        tree.get.left.get.left.get.right.get.parent = tree.get.left.get.left
        tree.get.left.get.right.get.right.get.parent = tree.get.left.get.right
        InOrderSuccessor(tree.get.left.get.left.get.right.get) shouldBe tree.get.left
        InOrderSuccessor(tree.get.left.get.right.get.right.get) shouldBe tree
      }
    }
  }
}
