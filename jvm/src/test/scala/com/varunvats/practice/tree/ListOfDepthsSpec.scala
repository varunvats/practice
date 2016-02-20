package com.varunvats.practice.tree

import com.varunvats.practice.sorting.UnitSpec

class ListOfDepthsSpec extends UnitSpec {

  val given = afterWord("given")

  "The list of depths creator" must {

    "create a list containing the right sequence of elements" when given {
      "a tree containing only one node" in {
        ListOfDepths(BinaryTreeNode(5)) shouldBe Seq(Seq(BinaryTreeNode(5)))
      }

      "a tree containing two nodes" in {
        val tree = BinaryTreeNode(5, Some(BinaryTreeNode(6)))
        val expectedDepthList = Seq(
          Seq(BinaryTreeNode(5)),
          Seq(BinaryTreeNode(6))
        )
        ListOfDepths(tree) shouldBe expectedDepthList
      }

      "a perfect binary tree consisting of 3 nodes" in {
        val tree = BinaryTreeNode(5, Some(BinaryTreeNode(6)), Some(BinaryTreeNode(7)))
        val expectedDepthList = Seq(
          Seq(BinaryTreeNode(5)),
          Seq(BinaryTreeNode(7), BinaryTreeNode(6))
        )
        ListOfDepths(tree) shouldBe expectedDepthList
      }

      "a tree consisting of 5 nodes that is essentially a linked-list (root is start of list)" in {
        val tree = BinaryTreeNode(5,
          left = Some(BinaryTreeNode(6,
            right = Some(BinaryTreeNode(7,
              left = Some(BinaryTreeNode(8,
                left = Some(BinaryTreeNode(9))))))))
        )
        val expectedDepthList = Seq(
          Seq(BinaryTreeNode(5)),
          Seq(BinaryTreeNode(6)),
          Seq(BinaryTreeNode(7)),
          Seq(BinaryTreeNode(8)),
          Seq(BinaryTreeNode(9))
        )
        ListOfDepths(tree) shouldBe expectedDepthList
      }

      "a tree consisting of 5 nodes that is essentially a linked list (root is middle element of list)" in {
        val tree = BinaryTreeNode(5,
          left = Some(BinaryTreeNode(6,
            right = Some(BinaryTreeNode(7)))),
          right = Some(BinaryTreeNode(8,
            right = Some(BinaryTreeNode(9))))
        )
        val expectedDepthList = Seq(
          Seq(BinaryTreeNode(5)),
          Seq(BinaryTreeNode(8), BinaryTreeNode(6)),
          Seq(BinaryTreeNode(9), BinaryTreeNode(7))
        )
        ListOfDepths(tree) shouldBe expectedDepthList
      }

      "a tree that is neither full nor complete" in {
        val tree = BinaryTreeNode(5,
          left = Some(BinaryTreeNode(6,
            left = Some(BinaryTreeNode(7)),
            right = Some(BinaryTreeNode(8)))),
          right = Some(BinaryTreeNode(11,
            right = Some(BinaryTreeNode(12,
              left = Some(BinaryTreeNode(13))))))
        )
        val expectedDepthList = Seq(
          Seq(BinaryTreeNode(5)),
          Seq(BinaryTreeNode(11), BinaryTreeNode(6)),
          Seq(BinaryTreeNode(12), BinaryTreeNode(8), BinaryTreeNode(7)),
          Seq(BinaryTreeNode(13))
        )
        ListOfDepths(tree) shouldBe expectedDepthList
      }
    }
  }
}
