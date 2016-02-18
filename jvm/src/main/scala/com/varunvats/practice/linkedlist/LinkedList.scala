package com.varunvats.practice.linkedlist

object LinkedList {

  case class LLNode[T](data: T, var next: Option[LLNode[T]] = None)

  def insert[T](head: LLNode[T], data: T, pos: Int): LLNode[T] = {
    if (pos < 0)
      throw new IndexOutOfBoundsException(s"Index $pos must be greater than or equal to 0")
    if (pos == 0)
      return LLNode[T](data, Some(head))
    var prev: Option[LLNode[T]] = Some(head)
    var i = 1
    while (i < pos) {
      if (prev.isEmpty)
        throw new IndexOutOfBoundsException(s"Position $pos must be lesser than or equal to the length of the list")
      prev = prev.get.next
      i = i + 1
    }
    if (prev.isEmpty)
      throw new IndexOutOfBoundsException(s"Position $pos must be lesser than or equal to the length of the list")
    prev.get.next = Some(LLNode(data, prev.get.next))
    head
  }
}
