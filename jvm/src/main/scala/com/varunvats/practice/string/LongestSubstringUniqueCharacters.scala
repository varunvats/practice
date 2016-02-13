package com.varunvats.practice.string

object LongestSubstringUniqueCharacters {

  def find(haystack: String): String = {

    def getNewLongestAndFrom(longestSoFar: String, from: Int, cursor: Int) = {
      val newLongestSoFar = if (cursor - from > longestSoFar.length)
        haystack.substring(from, cursor)
      else
        longestSoFar
      val newFrom = haystack.indexOf(haystack(cursor), from) + 1
      (newLongestSoFar, newFrom)
    }

    val (longestSubString, _) =
      haystack.indices.foldLeft(("", 0)) { case ((longestSoFar, from), cursor) =>
        if (haystack.view(from, cursor).contains(haystack(cursor))) {
          getNewLongestAndFrom(longestSoFar, from, cursor)
        } else if (cursor + 1 == haystack.length && cursor + 1 - from > longestSoFar.length) {
          (haystack.substring(from), from)
        } else {
          (longestSoFar, from)
        }
      }
    longestSubString
  }
}
