package com.varunvats.practice.tree.bst

import com.varunvats.practice.sorting.UnitSpec
import com.varunvats.practice.tree.BinaryTreeNode

class AllPossibleSequencesSpec extends UnitSpec {

  "All possible sequences that create a binary search tree" must {

    "be identified" when {
      "the tree contains only one node" in {
        val tree = BinaryTreeNode(5)
        AllPossibleSequences(tree) should contain theSameElementsAs Seq(Seq(5))
      }

      "the tree contains two nodes" in {
        val tree = BinaryTreeNode(5, left = Some(BinaryTreeNode(4)))
        AllPossibleSequences(tree) should contain theSameElementsAs Seq(Seq(5, 4))
      }

      "the tree contains three nodes (two on either side of root)" in {
        val tree = BinaryTreeNode(5,
          left = Some(BinaryTreeNode(4)),
          right = Some(BinaryTreeNode(6)))
        AllPossibleSequences(tree) should contain theSameElementsAs Seq(Seq(5, 4, 6), Seq(5, 6, 4))
      }

      "the tree contains three nodes (both on right side of root)" in {
        val tree = BinaryTreeNode(5,
          right = Some(BinaryTreeNode(7,
            left = Some(BinaryTreeNode(6)))))
        AllPossibleSequences(tree) should contain theSameElementsAs Seq(Seq(5, 7, 6))
      }

      "the tree contains several nodes" in {
        val tree = BinaryTreeNode(10,
          left = Some(BinaryTreeNode(7,
            left = Some(BinaryTreeNode(6)),
            right = Some(BinaryTreeNode(9)))),
          right = Some(BinaryTreeNode(12,
            right = Some(BinaryTreeNode(13)))))
        val allPossibleSequences = Seq(
          Seq(10, 7, 6, 9, 12, 13),
          Seq(10, 7, 6, 12, 9, 13),
          Seq(10, 7, 12, 6, 9, 13),
          Seq(10, 12, 7, 6, 9, 13),
          Seq(10, 7, 6, 12, 13, 9),
          Seq(10, 7, 12, 6, 13, 9),
          Seq(10, 12, 7, 6, 13, 9),
          Seq(10, 7, 12, 13, 6, 9),
          Seq(10, 12, 7, 13, 6, 9),
          Seq(10, 12, 13, 7, 6, 9),
          Seq(10, 7, 9, 6, 12, 13),
          Seq(10, 7, 9, 12, 6, 13),
          Seq(10, 7, 12, 9, 6, 13),
          Seq(10, 12, 7, 9, 6, 13),
          Seq(10, 7, 9, 12, 13, 6),
          Seq(10, 7, 12, 9, 13, 6),
          Seq(10, 12, 7, 9, 13, 6),
          Seq(10, 7, 12, 13, 9, 6),
          Seq(10, 12, 7, 13, 9, 6),
          Seq(10, 12, 13, 7, 9, 6)
        )
        AllPossibleSequences(tree) should contain theSameElementsAs allPossibleSequences
      }
    }
  }

}
