package com.varunvats.practice.tree

object CheckBalanced {

  def apply[T](tree: Option[BinaryTreeNode[T]]): Boolean = {
    val (_, balanced) = isBalanced(tree)
    balanced
  }

  private def isBalanced[T](nodeO: Option[BinaryTreeNode[T]]): (Int, Boolean) = {
    nodeO.fold((-1, true)) { node =>
      val left = isBalanced(node.left)
      val right = isBalanced(node.right)
      val height = Math.max(left._1, right._1) + 1
      val balanced = left._2 && right._2 && Math.abs(left._1 - right._1) <= 1
      (height, balanced)
    }
  }
}
