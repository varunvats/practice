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

  def lastRecursive[T](head: LLNode[T], k: Int): Option[LLNode[T]] = {
    val (lastO, _) = lastRecursive(Some(head), k)
    lastO
  }

  def lastIterative[T](head: LLNode[T], k: Int): Option[LLNode[T]] = {
    val kthNodeO = getKthNode(head, 1, k)
    kthNodeO.map(slide(head, _))
  }

  @tailrec
  private def slide[T](trailingPointer: LLNode[T], leadingPointer: LLNode[T]): LLNode[T] =
    leadingPointer.next match {
      case None => trailingPointer
      case Some(nextLeadingPointer) => slide(trailingPointer.next.get, nextLeadingPointer)
    }

  @tailrec
  private def getKthNode[T](node: LLNode[T], i: Int, k: Int): Option[LLNode[T]] = {
    if (i == k)
      return Some(node)
    node.next match {
      case None => None
      case Some(nextNode) => getKthNode(nextNode, i + 1, k)
    }
  }

  private def lastRecursive[T](nodeO: Option[LLNode[T]], k: Int): (Option[LLNode[T]], Int) =
    nodeO.fold((Option.empty[LLNode[T]], 0)) { node =>
      val result = lastRecursive(node.next, k)
      result match {
        case (kth@Some(_), numNodes) => (kth, numNodes + 1)
        case (_, numNodes) if numNodes + 1 == k => (nodeO, numNodes + 1)
        case (_, numNodes) => (None, numNodes + 1)
      }
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
