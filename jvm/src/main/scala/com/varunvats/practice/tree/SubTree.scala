package com.varunvats.practice.tree

object SubTree {

  case class Node[T](data: T, left: Option[Node[T]] = None, right: Option[Node[T]] = None)

  def apply[T](haystack: Node[T], needle: Node[T]): Boolean =
    apply(Some(haystack), Some(needle))

  private def apply[T](haystack: Option[Node[T]], needle: Option[Node[T]]): Boolean =
    treesMatch(haystack, needle) ||
      apply(haystack.flatMap(_.left), needle) ||
      apply(haystack.flatMap(_.right), needle)

  private def treesMatch[T](haystack: Option[Node[T]], needle: Option[Node[T]]): Boolean = {
    if (needle.isEmpty)
      return haystack.isEmpty
    haystack.get.data == needle.get.data &&
      treesMatch(haystack.flatMap(_.left), needle.flatMap(_.left)) &&
      treesMatch(haystack.flatMap(_.right), needle.flatMap(_.right))
  }

  def apply2[T](haystack: Node[T], needle: Node[T]): Boolean =
    apply2(Some(haystack), Some(needle))

  private def apply2[T](haystack: Option[Node[T]], needle: Option[Node[T]]): Boolean =
    haystack == needle ||
      apply2(haystack.flatMap(_.left), needle) ||
      apply2(haystack.flatMap(_.right), needle)
}
