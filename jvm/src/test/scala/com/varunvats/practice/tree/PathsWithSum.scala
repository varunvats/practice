package com.varunvats.practice.tree


object PathsWithSum {

  def apply(tree: BinaryTreeNode[Int], sum: Int): Int = {
    val (allSums, allNodes) = apply(Some(tree))
    val allPossibleSums = allSums ++: allNodes
    allPossibleSums.count(_ == sum)
  }

  private def apply(nodeO: Option[BinaryTreeNode[Int]]): (Seq[Int], Seq[Int]) =
    nodeO.fold((Seq.empty[Int], Seq.empty[Int])) { node =>
      val leftResult = apply(node.left)
      val rightResult = apply(node.right)
      merge(leftResult, rightResult, node)
    }

  private def merge(leftResult: (Seq[Int], Seq[Int]),
                    rightResult: (Seq[Int], Seq[Int]),
                    node: BinaryTreeNode[Int]): (Seq[Int], Seq[Int]) = {
    val (leftSums, leftNodes) = leftResult
    val (rightSums, rightNodes) = rightResult
    val leftValO = node.left.map(_.data)
    val rightValO = node.right.map(_.data)
    val newSums = (leftValO ++: rightValO ++: leftSums ++: rightSums).map(s => s + node.data)
    val allSums = newSums ++: leftSums ++: rightSums
    val allNodes = node.data +: leftNodes ++: rightNodes
    (allSums, allNodes)
  }

}
