package com.varunvats.practice.linkedlist

import com.varunvats.practice.sorting.UnitSpec

class PalindromeCheckerRecursiveSpec extends UnitSpec {

  "Palindrome checker (recursive)" should {
    "return false when passed a null" in {
      PalindromeCheckerRecursive.isPalindrome(null) shouldBe false
    }

    "return true when passed a linked-list with one element" in {
      // 5
      val list = new Node(5)
      PalindromeCheckerRecursive.isPalindrome(list) shouldBe true
    }

    "return true when passed a linked list containing two elements that have the same value" in {
      // 5 -> 5
      val list = new Node(5, new Node(5))
      PalindromeCheckerRecursive.isPalindrome(list) shouldBe true
    }

    "return false when passed a linked list containing two different elements" in {
      // 5 -> 6
      val list = new Node(5, new Node(6))
      PalindromeCheckerRecursive.isPalindrome(list) shouldBe false
    }

    "return true when passed in a palindrome list containing three elements" in {
      // 5 -> 6 -> 5
      val list = new Node(5, new Node(6, new Node(5)))
      PalindromeCheckerRecursive.isPalindrome(list) shouldBe true
    }

    "return false when passed a non-palindrome list containing three elements" in {
      // 5 -> 6 -> 7
      val list = new Node(5, new Node(6, new Node(7)))
      PalindromeCheckerRecursive.isPalindrome(list) shouldBe false
    }

    "return true when passed a palindrome list containing four elements" in {
      // 5 -> 6 -> 6 -> 5
      val list = new Node(5, new Node(6, new Node(6, new Node(5))))
      PalindromeCheckerRecursive.isPalindrome(list) shouldBe true
    }

    "return false when passed a non-palindrome list containing four elements" in {
      // 5 -> 6 -> 5 -> 6
      val list = new Node(5, new Node(6, new Node(5, new Node(6))))
      PalindromeCheckerRecursive.isPalindrome(list) shouldBe false
    }
  }

}
