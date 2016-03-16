package com.varunvats.practice.matrix

object LongestIncreasingPathLength {

  private val ZeroLength = 0

  def apply(matrix: Seq[Seq[Int]]): Int = {
    val cache = initializeCache(matrix)
    matrix.indices.foldLeft(ZeroLength) { (longestIncPathLenLastRow, rowNum) =>
      matrix(rowNum).indices.foldLeft(longestIncPathLenLastRow) {
        (longestIncPathLenSoFar, colNum) =>
          val newLongestIncPathLen = getLongestIncPathLen(matrix, cache, rowNum, colNum)
          Math.max(newLongestIncPathLen, longestIncPathLenSoFar)
      }
    }
  }

  private def getLongestIncPathLen(matrix: Seq[Seq[Int]],
                                   cache: Array[Array[Option[Int]]],
                                   rowNum: Int, colNum: Int): Int = {
    if (cache(rowNum)(colNum).nonEmpty)
      return cache(rowNum)(colNum).get
    val neighbors = getNeighbors(matrix, rowNum, colNum)
    val neighborLongestIncPathLen = neighbors.foldLeft(ZeroLength) {
      case (maxLenSoFar, (neighRowNum, neighColNum)) =>
        val neighMaxLen = getLongestIncPathLen(matrix, cache, neighRowNum, neighColNum)
        Math.max(neighMaxLen, maxLenSoFar)
    }
    val longestIncPathLen = neighborLongestIncPathLen + 1
    cache(rowNum)(colNum) = Some(longestIncPathLen)
    longestIncPathLen
  }

  private def getNeighbors(matrix: Seq[Seq[Int]], rowNum: Int, colNum: Int): Seq[(Int, Int)] = {
    val m = matrix.length
    val n = matrix.head.length
    val currVal = matrix(rowNum)(colNum)
    var neighbors = List.empty[(Int, Int)]
    if (rowNum != 0 && matrix(rowNum - 1)(colNum) > currVal) // Top
      neighbors = (rowNum - 1, colNum) +: neighbors
    if (colNum != n - 1 && matrix(rowNum)(colNum + 1) > currVal) // Right
      neighbors = (rowNum, colNum + 1) +: neighbors
    if (rowNum != m - 1 && matrix(rowNum + 1)(colNum) > currVal) // Bottom
      neighbors = (rowNum + 1, colNum) +: neighbors
    if (colNum != 0 && matrix(rowNum)(colNum - 1) > currVal) // Left
      neighbors = (rowNum, colNum - 1) +: neighbors
    neighbors
  }

  private def initializeCache(matrix: Seq[Seq[Int]]): Array[Array[Option[Int]]] = {
    val numRows = matrix.length
    val numCols = matrix.head.length
    Array.fill(numRows)(Array.fill(numCols)(Option.empty[Int]))
  }

}
