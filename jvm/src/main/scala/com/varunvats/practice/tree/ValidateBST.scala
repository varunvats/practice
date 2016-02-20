package com.varunvats.practice.tree

object ValidateBST {

  def apply[T](tree: BinaryTreeNode[T])(implicit ord: Ordering[T]): Boolean = {
    val PartialResult(isBST, _, _) = validateBST(tree)
    isBST
  }

  private def validateBST[T](node: BinaryTreeNode[T])(implicit ord: Ordering[T]): PartialResult[T] = {
    import ord._
    (node.left, node.right) match {
      case (None, None) => PartialResult(isBST = true, node.data, node.data)
      case (Some(left), None) =>
        val leftResult = validateBST(left)
        val isBST = leftResult.isBST && leftResult.max <= node.data
        PartialResult(isBST, min(leftResult.min, node.data), max(leftResult.max, node.data))
      case (None, Some(right)) =>
        val rightResult = validateBST(right)
        val isBST = rightResult.isBST && node.data < rightResult.min
        PartialResult(isBST, min(rightResult.min, node.data), max(rightResult.max, node.data))
      case (Some(left), Some(right)) =>
        val leftResult = validateBST(left)
        val rightResult = validateBST(right)
        val isBST = leftResult.isBST && rightResult.isBST && leftResult.max <= node.data && node.data < rightResult.min
        val minValue = min(leftResult.min, min(node.data, rightResult.min))
        val maxValue = max(leftResult.max, max(node.data, rightResult.max))
        PartialResult(isBST, minValue, maxValue)
    }
  }

  private case class PartialResult[T](isBST: Boolean, min: T, max: T)

}
