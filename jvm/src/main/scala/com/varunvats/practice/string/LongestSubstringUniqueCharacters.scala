package com.varunvats.practice.string

import scala.annotation.tailrec

object LongestSubstringUniqueCharacters {

  def find(hayStack: String): String = {
    find(hayStack, 0, "")
  }

  @tailrec
  private def find(hayStack: String, start: Int, longestSubSequence: String): String = {
    val (nextSubSequence, cursorO) = getLongestSubSequence(hayStack, start, start)
    val newLongestSubSequence =
      if (nextSubSequence.length > longestSubSequence.length)
        nextSubSequence
      else
        longestSubSequence
    cursorO match {
      case None => newLongestSubSequence
      case Some(cursor) => find(hayStack, cursor, newLongestSubSequence)
    }
  }

  @tailrec
  private def getLongestSubSequence(hayStack: String, from: Int, cursor: Int)
  : (String, Option[Int]) = {
    if (cursor >= hayStack.length)
      (hayStack.substring(from), None)
    else if (hayStack.view(from, cursor).contains(hayStack(cursor)))
      (hayStack.substring(from, cursor), Some(hayStack.indexOf(hayStack(cursor), from) + 1))
    else
      getLongestSubSequence(hayStack, from, cursor + 1)
  }

}
