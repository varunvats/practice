package com.varunvats.practice.queue

import java.util.NoSuchElementException

import scala.annotation.tailrec
import scala.collection.mutable.ArrayBuffer


class PriorityQueue[T](implicit ord: Ordering[T]) {

  import ord._

  private val arr = ArrayBuffer[T]()

  def +=(elem: T): PriorityQueue[T] = {
    if (arr.isEmpty)
      arr += elem
    arr += elem
    swim(length)
    this
  }

  def ++=(elems: Traversable[T]): PriorityQueue[T] = {
    elems.foreach(+=)
    this
  }

  def dequeue: T = {
    val t = head
    arr(1) = arr.last
    arr.remove(length)
    sink(1)
    t
  }

  def head: T = {
    if (length == 0)
      throw new NoSuchElementException("There are no elements in the queue.")
    arr(1)
  }

  def length: Int =
    if (arr.isEmpty)
      0
    else
      arr.size - 1

  def size: Int = length

  def isEmpty: Boolean = length == 0

  @tailrec
  private def swim(childIndex: Int): Unit = {
    val parentIndex = childIndex / 2
    if (parentIndex >= 1 && arr(childIndex) > arr(parentIndex)) {
      exchange(parentIndex, childIndex)
      swim(parentIndex)
    }
  }

  @tailrec
  private def sink(parentIndex: Int): Unit = {
    val child1Index = 2 * parentIndex
    val child2Index = child1Index + 1
    if (arr.isDefinedAt(child1Index) && arr.isDefinedAt(child2Index)) {
      val childSwapIndex = if (arr(child1Index) > arr(child2Index)) child1Index else child2Index
      if (arr(parentIndex) < arr(childSwapIndex)) {
        exchange(parentIndex, childSwapIndex)
        sink(childSwapIndex)
      }
    } else if (arr.isDefinedAt(child1Index) && arr(parentIndex) < arr(child1Index)) {
      exchange(parentIndex, child1Index)
      sink(child1Index)
    }
  }

  private def exchange(i: Int, j: Int): Unit = {
    val t: T = arr(i)
    arr(i) = arr(j)
    arr(j) = t
  }
}


object PriorityQueue {

  def apply[T](elems: T*)(implicit ord: Ordering[T]) = {
    val pq = new PriorityQueue[T]()
    pq ++= elems
    pq
  }
}
