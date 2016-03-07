package com.varunvats.practice.tree

import com.varunvats.practice.sorting.UnitSpec

class PreOrderSerializedSpec extends UnitSpec {

  "A string" must {

    "not be a valid a pre-order serialized binary tree" when {
      "empty" in {
        PreOrderSerialized("") shouldBe false
      }

      "it is not properly terminated with a '#'" in {
        PreOrderSerialized("1,#") shouldBe false
      }

      "it has an extra '#' in between" in {
        PreOrderSerialized("9,3,4,#,#,#,1,#,#,2,#,6,#,#") shouldBe false
      }

      "it has extra '#'s at the end" in {
        val ser = "9,#,#,#"
        PreOrderSerialized(ser) shouldBe false
      }

      "it has extra numbers at the end" in {
        val ser = "9,#,#,3"
        PreOrderSerialized(ser) shouldBe false
      }
    }

    "be a valid pre-order serialized binary tree" when {
      "it represents a tree with only one node" in {
        val ser = "9,#,#"
        PreOrderSerialized(ser) shouldBe true
      }

      "it represents a tree containing several nodes" in {
        val ser = "9,3,4,#,#,1,#,#,2,#,6,#,#"
        PreOrderSerialized(ser) shouldBe true
      }
    }
  }
}
