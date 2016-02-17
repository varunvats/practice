package com.varunvats.practice.graph

import scala.collection.mutable

object WordChain {

  def findLongestValidWord(seed: Char, dictionary: Set[String]): String = {
    findLongestValidWord(seed.toString, dictionary, mutable.Set(seed.toString))
  }

  private def findLongestValidWord(word: String, dictionary: Set[String], visited: mutable.Set[String]): String = {
    val nextValidWords = getNextValidWords(word, dictionary)
    nextValidWords.foldLeft(word) { (longestSoFar, nextValidWord) =>
      if (!visited(nextValidWord)) {
        val candidate = findLongestValidWord(nextValidWord, dictionary, visited += nextValidWord)
        if (candidate.length > longestSoFar.length) candidate else longestSoFar
      } else {
        longestSoFar
      }
    }
  }

  private def getNextValidWords(word: String, dictionary: Set[String]): List[String] = {
    ('a' to 'z').foldLeft(List.empty[String]) { (outerCollection, char) =>
      (0 to word.length).foldLeft(outerCollection) { (innerCollection, index) =>
        val possibleWord = insert(word, char, index)
        if (dictionary(possibleWord))
          possibleWord +: innerCollection
        else
          innerCollection
      }
    }
  }

  private def insert(word: String, char: Char, index: Int): String = {
    val (prefix, suffix) = word.splitAt(index)
    prefix + char + suffix
  }
}
