package com.varunvats.practice.tree

object FirstCommonAncestor {

  def apply[T](tree: BinaryTreeNode[T], node1Data: T, node2Data: T): Option[BinaryTreeNode[T]] = {
    // Assumption: all nodes of the tree are unique (i.e. no values repeat).
    val (_, _, firstCommonAncestorO) = apply(Some(tree), node1Data, node2Data)
    firstCommonAncestorO
  }

  private def apply[T](nodeO: Option[BinaryTreeNode[T]],
                       node1Data: T,
                       node2Data: T): (Boolean, Boolean, Option[BinaryTreeNode[T]]) =
    nodeO.fold((false, false, Option.empty[BinaryTreeNode[T]])) { node =>
      val (node1Found, node2Found, fcAncestorO) = checkWhichNodesAreFound(node, node1Data, node2Data)
      (node1Found, node2Found, fcAncestorO) match {
        case (false, false, _) =>
          (node1Data == node.data, node2Data == node.data, None)
        case (false, true, _) =>
          (node1Data == node.data, true, None)
        case (true, false, _) =>
          (true, node2Data == node.data, None)
        case (true, true, None) =>
          (true, true, Some(node))
        case (true, true, firstCommonAncestor@Some(_)) =>
          (true, true, firstCommonAncestor)
      }
    }

  private def checkWhichNodesAreFound[T](node: BinaryTreeNode[T],
                                         node1Data: T,
                                         node2Data: T): (Boolean, Boolean, Option[BinaryTreeNode[T]]) = {
    val (node1OnLeft, node2OnLeft, fcAncestorOnLeftO) = apply(node.left, node1Data, node2Data)
    val (node1OnRight, node2OnRight, fcAncestorOnRightO) = apply(node.right, node1Data, node2Data)
    val node1Found = node1OnLeft || node1OnRight
    val node2Found = node2OnLeft || node2OnRight
    val fcAncestorO = fcAncestorOnLeftO.orElse(fcAncestorOnRightO)
    (node1Found, node2Found, fcAncestorO)
  }
}
