package com.varunvats.practice.tree

import com.varunvats.practice.sorting.UnitSpec

class CheckBalancedSpec extends UnitSpec {

  "Balanced binary tree checker" must {

    "return true" when {
      "there is no tree (None)" in {
        CheckBalanced(None) shouldBe true
      }

      "the tree contains only one node" in {
        val tree = Some(BinaryTreeNode(5))
        CheckBalanced(tree) shouldBe true
      }

      "the tree contains two nodes (root's child is on its right)" in {
        val tree = Some(BinaryTreeNode(5, right = Some(BinaryTreeNode(7))))
        CheckBalanced(tree) shouldBe true
      }

      "the tree contains two nodes (root's child is on its left)" in {
        val tree = Some(BinaryTreeNode(5, left = Some(BinaryTreeNode(7))))
        CheckBalanced(tree) shouldBe true
      }

      "the tree has three nodes (root flanked by two nodes on either side)" in {
        val tree = Some(BinaryTreeNode(5,
          left = Some(BinaryTreeNode(7)),
          right = Some(BinaryTreeNode(8))))
        CheckBalanced(tree) shouldBe true
      }

      "the tree's root has two sub-trees whose height differ by 1" in {
        val tree = Some(BinaryTreeNode(1,
          left = Some(BinaryTreeNode(2,
            right = Some(BinaryTreeNode(11)))),
          right = Some(BinaryTreeNode(3,
            left = Some(BinaryTreeNode(4)),
            right = Some(BinaryTreeNode(5,
              left = Some(BinaryTreeNode(6))))))))
        CheckBalanced(tree) shouldBe true
      }

      "the tree is complete" in {
        val tree = Some(BinaryTreeNode(1,
          left = Some(BinaryTreeNode(2,
            left = Some(BinaryTreeNode(4)))),
          right = Some(BinaryTreeNode(3))))
        CheckBalanced(tree) shouldBe true
      }
    }

    "return false" when {
      "the tree has three nodes, two nodes are on the same side of the root" in {
        val tree = Some(BinaryTreeNode(5,
          right = Some(BinaryTreeNode(6,
            left = Some(BinaryTreeNode(7))))))
        CheckBalanced(tree) shouldBe false
      }

      "the tree's root has two sub-trees whose height differ by 2" in {
        val tree = Some(BinaryTreeNode(1,
          left = Some(BinaryTreeNode(2)),
          right = Some(BinaryTreeNode(3,
            left = Some(BinaryTreeNode(4)),
            right = Some(BinaryTreeNode(5,
              left = Some(BinaryTreeNode(6))))))))
        CheckBalanced(tree) shouldBe false
      }

      "the tree's root has two sub-trees whose height differ by 3" in {
        val tree = Some(BinaryTreeNode(1,
          left = Some(BinaryTreeNode(2,
            left = Some(BinaryTreeNode(11)),
            right = Some(BinaryTreeNode(3,
              left = Some(BinaryTreeNode(4,
                right = Some(BinaryTreeNode(5,
                  right = Some(BinaryTreeNode(8)))))))))),
          right = Some(BinaryTreeNode(6,
            left = Some(BinaryTreeNode(7))))))
        CheckBalanced(tree) shouldBe false
      }
    }
  }
}
