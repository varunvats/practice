package com.varunvats.practice.string

import scala.annotation.tailrec

object PalindromeCheck {

  def apply(str: String): Boolean =
    isPalindrome(str, 0, str.length - 1)

  @tailrec
  private def isPalindrome(str: String, leftCursor: Int, rightCursor: Int): Boolean = {
    if (leftCursor >= rightCursor)
      return true
    str(leftCursor) == str(rightCursor) &&
      isPalindrome(str, leftCursor + 1, rightCursor - 1)
  }
}
