package com.varunvats.practice

package object tree {

  case class BinaryTreeNode[T](data: T, left: Option[BinaryTreeNode[T]] = None, right: Option[BinaryTreeNode[T]] = None)
}
