package com.varunvats.practice.array

object BinaryTree {

  case class Node[T](data: T, left: Option[Node[T]] = None, right: Option[Node[T]] = None)

  def create[T](array: Array[T])(implicit ord: Ordering[T]): Option[Node[T]] = {
    require(isSorted[T](array), "The input array must be sorted.")
    create(array, 0, array.length - 1)
  }

  private def create[T](array: Array[T], start: Int, end: Int): Option[Node[T]] = {
    if (end < start)
      return None
    val mid = (start + end) / 2
    val left = create(array, start, mid - 1)
    val right = create(array, mid + 1, end)
    Some(Node(array(mid), left, right))
  }

  private def isSorted[T](array: Array[T])(implicit ord: Ordering[T]): Boolean = array.sameElements(array.sorted)
}
