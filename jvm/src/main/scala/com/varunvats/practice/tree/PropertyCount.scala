package com.varunvats.practice.tree

object PropertyCount {

  def apply[T](tree: BinaryTreeNode[T]): Int = {
    val (count, _) = apply(Some(tree))
    count
  }

  private def apply[T](nodeO: Option[BinaryTreeNode[T]]): (Int, Boolean) = {
    nodeO.fold((0, true)) { node =>
      val thisNodePartiallyMeets = (node.left, node.right) match {
        case (Some(leftNode), None) => leftNode.data == node.data
        case (None, Some(rightNode)) => rightNode.data == node.data
        case (Some(leftNode), Some(rightNode)) => leftNode.data == node.data && rightNode.data == node.data
        case (None, None) => true
      }
      val (leftResult, rightResult) = (apply(node.left), apply(node.right))
      val thisNodeMeets = thisNodePartiallyMeets && leftResult._2 && rightResult._2
      val countSoFar = leftResult._1 + rightResult._1
      val newCount = if (thisNodeMeets) countSoFar + 1 else countSoFar
      (newCount, thisNodeMeets)
    }
  }
}
