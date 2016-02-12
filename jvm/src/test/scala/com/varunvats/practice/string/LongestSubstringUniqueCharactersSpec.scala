package com.varunvats.practice.string

import com.varunvats.practice.sorting.UnitSpec

class LongestSubstringUniqueCharactersSpec extends UnitSpec {

  val givenAString = afterWord("given a string")

  "The longest sub-string (containing unique character) finder" when givenAString {

    "that is empty" must {
      "return an empty string" in {
        LongestSubstringUniqueCharacters.find("") shouldBe ""
      }
    }

    "containing only one character" must {
      "return a string containing only that character" in {
        LongestSubstringUniqueCharacters.find("z") shouldBe "z"
      }
    }

    "containing two different characters" must {
      "return a string containing the same two characters" in {
        LongestSubstringUniqueCharacters.find("xz") shouldBe "xz"
      }
    }

    "containing three same characters" must {
      "return a string containing only one character" in {
        LongestSubstringUniqueCharacters.find("ppp") shouldBe "p"
      }
    }

    "containing the sub-string of interest at the beginning of the string" must {
      "return the sub-string of interest" in {
        val hayStack = "xapqmnox"
        val needle = "xapqmno"
        LongestSubstringUniqueCharacters.find(hayStack) shouldBe needle
      }
    }

    "containing the sub-string of interest at the end of the string" must {
      "return the sub-string of interest" in {
        val hayStack = "xapqmnoxab"
        val needle = "pqmnoxab"
        LongestSubstringUniqueCharacters.find(hayStack) shouldBe needle
      }
    }

    "containing the sub-string of interest in the middle of the string" must {
      "return the sub-string of interest" in {
        val hayStack = "xaoiabpolmnbo"
        val needle = "iabpolmn"
        LongestSubstringUniqueCharacters.find(hayStack) shouldBe needle
      }
    }

    "containing two sub-strings of interest" must {
      "return the sub-string of interest occurring first in the string" in {
        val hayStack = "pqlamobxyzp"
        val needle = "pqlamobxyz"
        LongestSubstringUniqueCharacters.find(hayStack) shouldBe needle
      }
    }
  }
}
