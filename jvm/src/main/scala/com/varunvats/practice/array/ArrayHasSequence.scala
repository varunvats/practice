package com.varunvats.practice.array

import scala.annotation.tailrec


object ArrayHasSequence {

  def apply(arr: Array[Int]): Boolean = {
    if (arr.isEmpty)
      return true
    createSentinels(arr)
    val L = arr.length
    isValidArray(arr, 0, L - 1) &&
      arrayHasSequence(arr, 1, L - 2)
  }

  private def arrayHasSequence(arr: Array[Int], startIndex: Int, stopIndex: Int): Boolean = {
    if (startIndex >= stopIndex)
      return true
    val partitionIndex = partition(arr, startIndex, startIndex + 1, stopIndex)
    isValidArray(arr, startIndex - 1, partitionIndex) &&
      isValidArray(arr, partitionIndex, stopIndex + 1) &&
      arrayHasSequence(arr, startIndex, partitionIndex - 1) &&
      arrayHasSequence(arr, partitionIndex + 1, stopIndex)
  }

  @tailrec
  private def partition(arr: Array[Int], startIndex: Int, leftCursor: Int, rightCursor: Int): Int = {
    val pivotValue = arr(startIndex)
    // The `>=' and `<=' checks below are required to avoid quadratic run-times under certain circumstances.
    // See pg. 292 in the Algorithms text.
    val newLeftCursor = arr.indexWhere(v => v >= pivotValue, leftCursor)
    val newRightCursor = arr.lastIndexWhere(v => v <= pivotValue, rightCursor)
    if (newLeftCursor >= newRightCursor) {
      swap(arr, startIndex, newRightCursor)
      return newRightCursor
    }
    swap(arr, newLeftCursor, newRightCursor)
    partition(arr, startIndex, newLeftCursor + 1, newRightCursor - 1)
  }

  private def isValidArray(arr: Array[Int], startIndex: Int, stopIndex: Int): Boolean = {
    val spotsAvailable = stopIndex - startIndex
    val numbersToPlace = arr(stopIndex) - arr(startIndex)
    spotsAvailable >= numbersToPlace
  }

  private def createSentinels(arr: Array[Int]): Unit = {
    var minValueIndex: Int = 0
    var maxValueIndex: Int = 0
    arr.indices.foreach { index =>
      if (arr(index) < arr(minValueIndex))
        minValueIndex = index
      else if (arr(index) > arr(maxValueIndex))
        maxValueIndex = index
    }
    if (maxValueIndex == 0) {
      maxValueIndex = minValueIndex
    }
    swap(arr, minValueIndex, 0)
    swap(arr, maxValueIndex, arr.length - 1)
  }

  private def swap(arr: Array[Int], index1: Int, index2: Int): Unit = {
    val t = arr(index1)
    arr(index1) = arr(index2)
    arr(index2) = t
  }

}
