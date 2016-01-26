package com.varunvats.practice.string

import com.varunvats.practice.sorting.UnitSpec

class StringCompressorSpec extends UnitSpec {

  "StringCompressor" should {
    "complain when asked to compress a null" in {
      intercept[IllegalArgumentException] {
        StringCompressor.compress(null)
      }
    }

    "return an empty string when given an empty string" in {
      StringCompressor.compress("") shouldBe ""
    }

    "return the correct string when the uncompressed string contains only 1 character" in {
      val uncompressed = "x"
      val compressed = "x1"
      StringCompressor.compress(uncompressed) shouldBe compressed
    }

    "return the correct string when the string cannot be compressed" in {
      val uncompressed = "abcdef"
      val compressed = "a1b1c1d1e1f1"
      StringCompressor.compress(uncompressed) shouldBe compressed
    }

    "compress a string that can be compressed" in {
      val uncompressed = "ppqrrrraamnoop"
      val compressed = "p2q1r4a2m1n1o2p1"
      StringCompressor.compress(uncompressed) shouldBe compressed
    }
  }

}
