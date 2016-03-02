package com.varunvats.practice.tree.bst

import com.varunvats.practice.tree.BinaryTreeNode

object AllPossibleSequences {

  def apply[T](bst: BinaryTreeNode[T]): Seq[Seq[T]] =
    (bst.left, bst.right) match {
      case (None, None) => Seq(Seq(bst.data))
      case (None, Some(right)) =>
        val rightWays = apply(right)
        rightWays.map(rightWay => bst.data +: rightWay)
      case (Some(left), None) =>
        val leftWays = apply(left)
        leftWays.map(leftWay => bst.data +: leftWay)
      case (Some(left), Some(right)) =>
        val leftWays = apply(left)
        val rightWays = apply(right)
        val allSequences = allPossibleSequences(leftWays, rightWays)
        allSequences.map(seq => bst.data +: seq)
    }

  private def allPossibleSequences[T](leftWays: Seq[Seq[T]], rightWays: Seq[Seq[T]]): Seq[Seq[T]] =
    leftWays.flatMap { leftWay =>
      rightWays.flatMap { rightWay =>
        merge(leftWay, rightWay)
      }
    }

  private def merge[T](left: Seq[T], right: Seq[T]): Seq[Seq[T]] = {
    val lastLeft = left.last
    val lastRight = right.last
    val l = create(Seq(lastLeft), left, left.length - 2, right, right.length - 1)
    val r = create(Seq(lastRight), left, left.length - 1, right, right.length - 2)
    l ++ r
  }

  private def create[T](suffix: Seq[T],
                        left: Seq[T], nextLeftIndex: Int,
                        right: Seq[T], nextRightIndex: Int): Seq[Seq[T]] = {
    (nextLeftIndex >= 0, nextRightIndex >= 0) match {
      case (false, false) =>
        Seq(suffix)
      case (false, true) =>
        create(right(nextRightIndex) +: suffix, left, nextLeftIndex, right, nextRightIndex - 1)
      case (true, false) =>
        create(left(nextLeftIndex) +: suffix, left, nextLeftIndex - 1, right, nextRightIndex)
      case (true, true) =>
        val l = create(left(nextLeftIndex) +: suffix, left, nextLeftIndex - 1, right, nextRightIndex)
        val r = create(right(nextRightIndex) +: suffix, left, nextLeftIndex, right, nextRightIndex - 1)
        l ++ r
    }
  }
}
