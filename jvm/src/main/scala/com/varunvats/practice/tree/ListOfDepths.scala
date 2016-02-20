package com.varunvats.practice.tree

import scala.collection.mutable

object ListOfDepths {

  def apply[T](tree: BinaryTreeNode[T]): Seq[Seq[BinaryTreeNode[T]]] = {
    val frontier = mutable.Queue((tree, 0))
    val listOfDepths = mutable.ArrayBuffer.empty[Seq[BinaryTreeNode[T]]]
    while (frontier.nonEmpty) {
      val (node, depth) = frontier.dequeue()
      if (!listOfDepths.isDefinedAt(depth))
        listOfDepths += Seq(node)
      else
        listOfDepths(depth) = node +: listOfDepths(depth)
      val nextDepth = depth + 1
      node.left.foreach(left => frontier += ((left, nextDepth)))
      node.right.foreach(right => frontier += ((right, nextDepth)))
    }
    listOfDepths
  }
}
