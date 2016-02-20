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

}
