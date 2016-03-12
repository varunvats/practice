package com.varunvats.practice.linkedlist

import com.varunvats.practice.linkedlist.LinkedList.LLNode

import scala.annotation.tailrec

object OddEven {

  def apply[T](head: LLNode[T]): Unit = {
    val (tail, length) = getTailAndLength(head, 1)
    if (length > 2)
      oddEven(head, 0, tail, length)
  }

  @tailrec
  private def oddEven[T](oddNode: LLNode[T], index: Int, tail: LLNode[T], length: Int): Unit = {
    if (index < length / 2) {
      val evenNodeO = oddNode.next
      val evenNode = evenNodeO.get
      oddNode.next = evenNode.next
      evenNode.next = None
      tail.next = evenNodeO
      oddEven(oddNode.next.get, index + 1, evenNode, length)
    }
  }

  @tailrec
  private def getTailAndLength[T](node: LLNode[T], length: Int): (LLNode[T], Int) = {
    if (node.next.isEmpty)
      return (node, length)
    getTailAndLength(node.next.get, length + 1)
  }
}
