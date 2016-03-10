package com.varunvats.practice.string

import scala.annotation.tailrec
import scala.collection.mutable

object SmallestWindowContainingCharSet {

  private type Window = (Int, Int)

  def apply(str: String, chars: Set[Char]): Option[Window] = {
    val startIndexO = getStartIndex(str, chars)
    startIndexO.flatMap { startIndex =>
      if (chars.size == 1)
        Some(startIndex, 1)
      else {
        val charCounts = mutable.Map(str(startIndex) -> 1)
        getWindow(str, chars, charCounts, startIndex, startIndex, None)
      }
    }
  }

  @tailrec
  private def getWindow(str: String, chars: Set[Char],
                        charCounts: mutable.Map[Char, Int], leftCursor: Int,
                        rightCursor: Int, minWindowO: Option[Window]): Option[Window] = {
    if (leftCursor > rightCursor)
      throw new IllegalStateException("Left cursor must never cross right cursor")
    if (charCounts.size != chars.size) {
      // Invalid window
      val newRightCursorO = moveRightCursor(str, chars, charCounts, rightCursor)
      newRightCursorO match {
        case None => minWindowO
        case Some(newRightCursor) =>
          getWindow(str, chars, charCounts, leftCursor, newRightCursor, minWindowO)
      }
    } else {
      // Valid window
      val windowLength = rightCursor - leftCursor + 1
      val newWindow = (leftCursor, windowLength)
      val newMinWindow = minWindowO.fold(Option(newWindow)) { minWindow =>
        if (windowLength < minWindow._2) Some(newWindow) else minWindowO
      }
      val newLeftCursor = moveLeftCursor(str, chars, charCounts, leftCursor)
      getWindow(str, chars, charCounts, newLeftCursor, rightCursor, newMinWindow)
    }
  }

  private def moveLeftCursor(str: String, chars: Set[Char],
                             charCounts: mutable.Map[Char, Int], leftCursor: Int): Int = {
    val charToPop = str(leftCursor)
    charCounts(charToPop) = charCounts(charToPop) - 1
    if (charCounts(charToPop) == 0)
      charCounts -= charToPop
    // newLeftCursor must never be < 0 since the left cursor is moved only when the window is valid
    val newLeftCursor = str.indexWhere(chars.contains, leftCursor + 1)
    newLeftCursor
  }

  @tailrec
  private def moveRightCursor(str: String, chars: Set[Char],
                              charCounts: mutable.Map[Char, Int], rightCursor: Int): Option[Int] = {
    val nextRightCursor = rightCursor + 1
    if (nextRightCursor >= str.length)
      return None
    val char = str(nextRightCursor)
    if (chars.contains(char)) {
      if (charCounts.contains(char))
        charCounts(char) = charCounts(char) + 1
      else
        charCounts += (char -> 1)
      if (charCounts.size == chars.size)
        return Some(nextRightCursor)
    }
    moveRightCursor(str, chars, charCounts, nextRightCursor)
  }

  private def getStartIndex(str: String, chars: Set[Char]): Option[Int] = {
    val startIndex = str.indexWhere(chars.contains)
    if (startIndex < 0)
      return None
    Some(startIndex)
  }
}
