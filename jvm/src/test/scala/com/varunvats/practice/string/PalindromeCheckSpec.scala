package com.varunvats.practice.string

import com.varunvats.practice.sorting.UnitSpec

class PalindromeCheckSpec extends UnitSpec {

  "A string" must {

    "be a palindrome" when {
      "empty" in {
        PalindromeCheck("") shouldBe true
      }

      "it contains only one character" in {
        PalindromeCheck("c") shouldBe true
      }

      "it contains only two characters, both of the same value" in {
        PalindromeCheck("cc") shouldBe true
      }

      "it contains only three characters, all of the same value" in {
        PalindromeCheck("ppp") shouldBe true
      }

      "it contains only three characters, and only the first and last are same" in {
        PalindromeCheck("opo") shouldBe true
      }

      "it contains an even number of characters that form a palindrome" in {
        PalindromeCheck("xaax") shouldBe true
      }

      "it contains an odd number of characters that form a palindrome" in {
        PalindromeCheck("omooppqppoomo") shouldBe true
      }
    }

    "not be a palindrome" when {
      "it contains two characters of different values" in {
        PalindromeCheck("pq") shouldBe false
      }

      "it contains three characters that don't form a palindrome" in {
        PalindromeCheck("ppq") shouldBe false
      }

      "it contains an even number of characters that don't form a palindrome" in {
        PalindromeCheck("papaapaq") shouldBe false
      }

      "it contains an odd number of characters that don't form a palindrome" in {
        PalindromeCheck("nonoponom") shouldBe false
      }
    }
  }

}
