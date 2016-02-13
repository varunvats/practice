package com.varunvats.practice.tree

import com.varunvats.practice.sorting.UnitSpec

class SubTreeSpec extends UnitSpec {

  "Sub-tree checker" must {

    "return true" when {
      "both the haystack and the needle have only one node and both the nodes are same" in {
        val haystack = BinaryTreeNode(5)
        val needle = BinaryTreeNode(5)
        SubTree(haystack, needle) shouldBe true
      }

      "the needle is the root of the haystack" in {
        val haystack = BinaryTreeNode(5, Some(BinaryTreeNode(6)), Some(BinaryTreeNode(11, Some(BinaryTreeNode(12)))))
        val needle = BinaryTreeNode(5)
        SubTree(haystack, needle) shouldBe true
      }

      "the needle is a leaf node of the haystack" in {
        val haystack = BinaryTreeNode(5, Some(BinaryTreeNode(6)), Some(BinaryTreeNode(11, Some(BinaryTreeNode(12)))))
        val needle = BinaryTreeNode(12)
        SubTree(haystack, needle) shouldBe true
      }

      "the needle is a single-node tree in the middle of the haystack" in {
        val haystack = BinaryTreeNode(5,
          Some(BinaryTreeNode(6)),
          Some(BinaryTreeNode(11, Some(BinaryTreeNode(12)), Some(BinaryTreeNode(64)))))
        val needle = BinaryTreeNode(11)
        SubTree(haystack, needle) shouldBe true
      }

      "the needle is in the middle of the haystack" in {
        val haystack = BinaryTreeNode(5,
          Some(BinaryTreeNode(6)),
          Some(BinaryTreeNode(11,
            Some(BinaryTreeNode(12, Some(BinaryTreeNode(11)), Some(BinaryTreeNode(13)))),
            Some(BinaryTreeNode(61, Some(BinaryTreeNode(16)), Some(BinaryTreeNode(50)))))))
        val needle = BinaryTreeNode(11, Some(BinaryTreeNode(12)), Some(BinaryTreeNode(61)))
        SubTree(haystack, needle) shouldBe true
      }

      "the leaf nodes of the haystack and the needle coincide" in {
        val haystack = BinaryTreeNode(5,
          Some(BinaryTreeNode(6)),
          Some(BinaryTreeNode(11,
            Some(BinaryTreeNode(12, Some(BinaryTreeNode(11)), Some(BinaryTreeNode(13)))),
            Some(BinaryTreeNode(61, Some(BinaryTreeNode(16)), Some(BinaryTreeNode(50)))))))
        val needle = BinaryTreeNode(61, Some(BinaryTreeNode(16)), Some(BinaryTreeNode(50)))
        SubTree(haystack, needle) shouldBe true
      }

      "the haystack and the needle area the same trees" in {
        val haystack = BinaryTreeNode(5,
          Some(BinaryTreeNode(6)),
          Some(BinaryTreeNode(11,
            Some(BinaryTreeNode(12, Some(BinaryTreeNode(11)), Some(BinaryTreeNode(13)))),
            Some(BinaryTreeNode(61, Some(BinaryTreeNode(16)), Some(BinaryTreeNode(50)))))))
        val needle = BinaryTreeNode(5,
          Some(BinaryTreeNode(6)),
          Some(BinaryTreeNode(11,
            Some(BinaryTreeNode(12, Some(BinaryTreeNode(11)), Some(BinaryTreeNode(13)))),
            Some(BinaryTreeNode(61, Some(BinaryTreeNode(16)), Some(BinaryTreeNode(50)))))))
        SubTree(haystack, needle) shouldBe true
      }
    }

    "return false" when {
      "both the haystack and the needle have only one node and the nodes are different" in {
        val haystack = BinaryTreeNode(5)
        val needle = BinaryTreeNode(6)
        SubTree(haystack, needle) shouldBe false
      }

      "the needle is almost (off by one) a sub-tree of the haystack" in {
        val haystack = BinaryTreeNode(5,
          Some(BinaryTreeNode(6)),
          Some(BinaryTreeNode(11,
            Some(BinaryTreeNode(12, Some(BinaryTreeNode(11)), Some(BinaryTreeNode(13)))),
            Some(BinaryTreeNode(61, Some(BinaryTreeNode(16)), Some(BinaryTreeNode(50)))))))
        val needle = BinaryTreeNode(11, Some(BinaryTreeNode(61)), Some(BinaryTreeNode(61)))
        SubTree(haystack, needle) shouldBe false
      }

      "the needle is larger than the haystack" in {
        val needle = BinaryTreeNode(5,
          Some(BinaryTreeNode(6)),
          Some(BinaryTreeNode(11,
            Some(BinaryTreeNode(12, Some(BinaryTreeNode(11)), Some(BinaryTreeNode(13)))),
            Some(BinaryTreeNode(61, Some(BinaryTreeNode(16)), Some(BinaryTreeNode(50)))))))
        val haystack = BinaryTreeNode(61, Some(BinaryTreeNode(16)), Some(BinaryTreeNode(50)))
        SubTree(haystack, needle) shouldBe false
      }
    }
  }
}
