package com.varunvats.practice

package object tree {

  final case class BinaryTreeNode[T](data: T,
                                     left: Option[BinaryTreeNode[T]] = None,
                                     right: Option[BinaryTreeNode[T]] = None) {

    override def equals(other: Any): Boolean = other match {
      case that: BinaryTreeNode[T] => that.canEqual(this) && this.data == that.data
      case _ => false
    }
  }

  final class BinaryTreeNodeP[T](val data: T,
                                 val left: Option[BinaryTreeNodeP[T]] = None,
                                 val right: Option[BinaryTreeNodeP[T]] = None,
                                 var parent: Option[BinaryTreeNodeP[T]] = None) {

    override def equals(other: Any): Boolean = other match {
      case that: BinaryTreeNodeP[T] => this.data == that.data
      case _ => false
    }
  }

  object BinaryTreeNodeP {
    def apply[T](data: T,
                 left: Option[BinaryTreeNodeP[T]] = None,
                 right: Option[BinaryTreeNodeP[T]] = None,
                 parent: Option[BinaryTreeNodeP[T]] = None) = {
      new BinaryTreeNodeP(data, left, right, parent)
    }
  }

}
