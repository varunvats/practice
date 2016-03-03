package com.varunvats.practice.linkedlist

import scala.annotation.tailrec
import scala.collection.mutable

object LinkedList {

  case class LLNode[T](var data: T, var next: Option[LLNode[T]] = None)

  def insert[T](head: LLNode[T], data: T, pos: Int): LLNode[T] = {
    if (pos == 0)
      return LLNode[T](data, Some(head))
    val prev = getNodePreceding(head, pos)
    prev.next = Some(LLNode(data, prev.next))
    head
  }

  def update[T](head: LLNode[T], data: T, pos: Int): Unit = {
    if (pos == 0)
      head.data = data
    else {
      val prev = getNodePreceding(head, pos)
      if (prev.next.isEmpty)
        throw new IndexOutOfBoundsException(s"Position $pos must be lesser than or equal to the length of the list")
      prev.next.get.data = data
    }
  }

  def removeDuplicates[T](head: LLNode[T]): Unit = {
    val seen = mutable.Set(head.data)
    removeDuplicates(head, seen)
  }

  @tailrec
  private def removeDuplicates[T](node: LLNode[T], seen: mutable.Set[T]): Unit = {
    node.next match {
      case Some(nextNode) if seen(nextNode.data) =>
        node.next = nextNode.next
        removeDuplicates(node, seen)
      case Some(nextNode) =>
        removeDuplicates(nextNode, seen += nextNode.data)
      case None =>
    }
  }

  private def getNodePreceding[T](head: LLNode[T], pos: Int): LLNode[T] = {
    if (pos < 0)
      throw new IndexOutOfBoundsException(s"Index $pos must be greater than or equal to 0")
    require(pos > 0, "Cannot get node preceding the head node")
    var prevO: Option[LLNode[T]] = Some(head)
    var i = 1
    while (i < pos) {
      if (prevO.isEmpty)
        throw new IndexOutOfBoundsException(s"Position $pos must be lesser than or equal to the length of the list")
      prevO = prevO.get.next
      i = i + 1
    }
    if (prevO.isEmpty)
      throw new IndexOutOfBoundsException(s"Position $pos must be lesser than or equal to the length of the list")
    prevO.get
  }
}
