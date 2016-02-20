package com.varunvats.practice.tree

import com.varunvats.practice.sorting.UnitSpec

class ValidateBSTSpec extends UnitSpec {

  "The BST validator" must {

    "return true" when {
      "the tree has only one node" in {
        ValidateBST(BinaryTreeNode(5)) shouldBe true
      }

      "the tree has two nodes, both having the same value, and the second node is the left child of the root" in {
        val tree = BinaryTreeNode(5, Some(BinaryTreeNode(5)))
        ValidateBST(tree) shouldBe true
      }

      "the tree has three nodes where the middle node is the root and is flanked by the smaller and larger nodes" in {
        val tree = BinaryTreeNode(5, Some(BinaryTreeNode(4)), Some(BinaryTreeNode(6)))
        ValidateBST(tree) shouldBe true
      }

      "the tree is a zig-zag sorted linked list and the root is the head of the linked-list" in {
        val tree = BinaryTreeNode(10,
          left = Some(BinaryTreeNode(1,
            right = Some(BinaryTreeNode(9,
              left = Some(BinaryTreeNode(2,
                right = Some(BinaryTreeNode(8,
                  left = Some(BinaryTreeNode(3,
                    right = Some(BinaryTreeNode(7)))))))))))))
        ValidateBST(tree) shouldBe true
      }

      "the tree is a sorted linked-list with one of the middle nodes of the list the root of the tree" in {
        val tree = BinaryTreeNode(5,
          left = Some(BinaryTreeNode(2,
            right = Some(BinaryTreeNode(3)))),
          right = Some(BinaryTreeNode(6,
            right = Some(BinaryTreeNode(7)))))
        ValidateBST(tree) shouldBe true
      }

      "the tree doesn't form a linked-list but is a BST" in {
        val tree = BinaryTreeNode(5,
          left = Some(BinaryTreeNode(3,
            left = Some(BinaryTreeNode(2)),
            right = Some(BinaryTreeNode(4)))),
          right = Some(BinaryTreeNode(10)))
        ValidateBST(tree) shouldBe true
      }
    }

    "return false" when {
      "the tree has two nodes, both having the same value, and the second node is the right child of the root" in {
        val tree = BinaryTreeNode(5, right = Some(BinaryTreeNode(5)))
        ValidateBST(tree) shouldBe false
      }

      "the tree is an unsorted linked-list" in {
        val tree = BinaryTreeNode(10,
          left = Some(BinaryTreeNode(1,
            right = Some(BinaryTreeNode(11,
              left = Some(BinaryTreeNode(2,
                right = Some(BinaryTreeNode(8,
                  left = Some(BinaryTreeNode(3,
                    right = Some(BinaryTreeNode(7)))))))))))))
        ValidateBST(tree) shouldBe false
      }

      "the root's right sub-tree has an element that is smaller than the root (tree doesn't form a linked-list)" in {
        val tree = BinaryTreeNode(10,
          left = Some(BinaryTreeNode(3,
            left = Some(BinaryTreeNode(2)),
            right = Some(BinaryTreeNode(4)))),
          right = Some(BinaryTreeNode(11,
            left = Some(BinaryTreeNode(7,
              left = Some(BinaryTreeNode(6)))))))
        ValidateBST(tree) shouldBe false
      }

      "the root's left sub-tree has an element that is greater than the root (tree doesn't form a linked-list)" in {
        val tree = BinaryTreeNode(5,
          left = Some(BinaryTreeNode(3,
            left = Some(BinaryTreeNode(2)),
            right = Some(BinaryTreeNode(11)))),
          right = Some(BinaryTreeNode(10,
            left = Some(BinaryTreeNode(7,
              left = Some(BinaryTreeNode(6)))))))
        ValidateBST(tree) shouldBe false
      }

      "the root's left sub-tree is not a BST" in {
        val tree = BinaryTreeNode(10,
          left = Some(BinaryTreeNode(8,
            left = Some(BinaryTreeNode(7)),
            right = Some(BinaryTreeNode(8)))),
          right = Some(BinaryTreeNode(12,
            left = Some(BinaryTreeNode(11)),
            right = Some(BinaryTreeNode(13)))))
        ValidateBST(tree) shouldBe false
      }

      "the root's right sub-tree is not a BST" in {
        val tree = BinaryTreeNode(10,
          left = Some(BinaryTreeNode(8,
            left = Some(BinaryTreeNode(7)),
            right = Some(BinaryTreeNode(9)))),
          right = Some(BinaryTreeNode(12,
            left = Some(BinaryTreeNode(11)),
            right = Some(BinaryTreeNode(12)))))
        ValidateBST(tree) shouldBe false
      }
    }
  }
}
