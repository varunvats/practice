package com.varunvats.practice.tree

object PreOrderSerialized {

  val Null = "#"

  def apply(serialized: String): Boolean = {
    val tokens = serialized.split(',')
    val treeEndIndexO = getTreeEndIndex(tokens, 0)
    treeEndIndexO.fold(false)(_ == tokens.length - 1)
  }

  private def getTreeEndIndex(tokens: Array[String], rootIndex: Int): Option[Int] = {
    if (rootIndex >= tokens.length)
      return None
    if (tokens(rootIndex) == Null)
      return Some(rootIndex)
    val leftTreeEndIndexO = getTreeEndIndex(tokens, rootIndex + 1)
    leftTreeEndIndexO.flatMap { leftTreeEndIndex =>
      getTreeEndIndex(tokens, leftTreeEndIndex + 1)
    }
  }
}
