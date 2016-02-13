package com.varunvats.practice.tree

object SubTree {

  def apply[T](haystack: BinaryTreeNode[T], needle: BinaryTreeNode[T]): Boolean =
    apply(Some(haystack), Some(needle))

  private def apply[T](haystackO: Option[BinaryTreeNode[T]], needleO: Option[BinaryTreeNode[T]]): Boolean =
    treesMatch(haystackO, needleO) ||
      haystackO.fold(false) { haystack =>
        apply(haystack.left, needleO) || apply(haystack.right, needleO)
      }

  private def treesMatch[T](haystackO: Option[BinaryTreeNode[T]], needleO: Option[BinaryTreeNode[T]]): Boolean =
    (haystackO, needleO) match {
      case (_, None) => true
      case (None, Some(_)) => false
      case (Some(haystack), Some(needle)) =>
        haystack.data == needle.data && treesMatch(haystack.left, needle.left) && treesMatch(haystack.right, needle.right)
    }
}
