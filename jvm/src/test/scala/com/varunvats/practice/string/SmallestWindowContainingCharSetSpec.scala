package com.varunvats.practice.string

import com.varunvats.practice.sorting.UnitSpec

class SmallestWindowContainingCharSetSpec extends UnitSpec {

  "The smallest window of a string containing the given set of characters" must {

    "not exist" when {
      "the string is empty" in {
        val str = ""
        val chars = Set('x', 'y')
        SmallestWindowContainingCharSet(str, chars) shouldBe None
      }

      "the char set is empty" in {
        val str = "abcdef"
        val chars = Set.empty[Char]
        SmallestWindowContainingCharSet(str, chars) shouldBe None
      }

      "both the string and the char set are empty" in {
        val str = ""
        val chars = Set.empty[Char]
        SmallestWindowContainingCharSet(str, chars) shouldBe None
      }

      "the char set contains only 1 char and the string does not contain that char" in {
        val str = "abcdefg"
        val chars = Set('o')
        SmallestWindowContainingCharSet(str, chars) shouldBe None
      }

      "the string does not contain all the chars that are in the set" in {
        val str = "qaerttoierozx"
        val chars = Set('q', 't', 'i', 'm')
        SmallestWindowContainingCharSet(str, chars) shouldBe None
      }
    }

    "be the entire string" when {
      "the string is the same as the char set" in {
        val str = "bac"
        val chars = Set('a', 'b', 'c')
        SmallestWindowContainingCharSet(str, chars) shouldBe Some(0, str.length)
      }

      "the string has the chars dispersed from the beginning to the end of the string" in {
        val str = "qpariomhn"
        val chars = Set('q', 'i', 'n')
        SmallestWindowContainingCharSet(str, chars) shouldBe Some(0, str.length)
      }

    }

    "be a substring of the original string" when {
      "the string contains all the chars in the set in a sequence in the middle of the string" in {
        val str = "qpariomhn"
        val chars = Set('o', 'i', 'r')
        SmallestWindowContainingCharSet(str, chars) shouldBe Some(3, 3)
      }

      "the chars are dispersed in the string and there are no duplicates" in {
        val str = "qpariomhn"
        val chars = Set('i', 'm', 'a')
        SmallestWindowContainingCharSet(str, chars) shouldBe Some(2, 5)
      }

      "the chars are dispersed in the string and there are duplicates" in {
        val str = "qapompomyqoieaeqysroieaymnmmqay"
        val chars = Set('q', 'i', 'a', 'y')
        SmallestWindowContainingCharSet(str, chars) shouldBe Some(8, 6)
      }
    }
  }
}
