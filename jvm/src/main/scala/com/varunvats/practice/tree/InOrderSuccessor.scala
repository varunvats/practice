package com.varunvats.practice.tree

import scala.annotation.tailrec

object InOrderSuccessor {

  def apply[T](of: BinaryTreeNodeP[T]): Option[BinaryTreeNodeP[T]] =
    of.right.fold(nextBiggestAncestor(of))(Some(_))

  @tailrec
  private def nextBiggestAncestor[T](of: BinaryTreeNodeP[T]): Option[BinaryTreeNodeP[T]] = {
    if (of.parent.isEmpty)
      return None
    val parent = of.parent.get
    if (parent.left.nonEmpty && parent.left.get == of)
      of.parent
    else
      nextBiggestAncestor(parent)
  }
}
