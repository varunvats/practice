package com.varunvats.practice.graph

import scala.collection.mutable

object NumIslands {

  def apply(implicit area: Seq[Seq[Int]]): Int = {
    val visited = Array.fill(area.length)(mutable.BitSet.empty)
    area.indices.foldLeft(0) { (numIslandsSoFar, rowIndex) =>
      area(rowIndex).foldLeft(numIslandsSoFar) { (numIslands, colIndex) =>
        if (isLand(rowIndex, colIndex) && !visited(rowIndex)(colIndex)) {
          visit(area, rowIndex, colIndex, visited)
          numIslands + 1
        } else {
          numIslands
        }
      }
    }
  }

  private def visit(area: Seq[Seq[Int]], rowIndex: Int, colIndex: Int,
                    visited: Array[mutable.BitSet]): Unit = {
    visited(rowIndex) += colIndex
    val landIndices = getNeighboringLandIndices(area, rowIndex, colIndex)
    landIndices.foreach { case (nRowIndex, nColIndex) =>
      if (!visited(nRowIndex)(nColIndex))
        visit(area, nRowIndex, nColIndex, visited)
    }
  }

  private def getNeighboringLandIndices(implicit area: Seq[Seq[Int]], rowIndex: Int,
                                        colIndex: Int): Seq[(Int, Int)] = {
    val m = area.length - 1
    val n = area(rowIndex).length - 1
    var indices = List.empty[(Int, Int)]
    val prevRowIndex = rowIndex - 1
    val prevColIndex = colIndex - 1
    val nextRowIndex = rowIndex + 1
    val nextColIndex = colIndex + 1
    if (rowIndex != 0) {
      if (colIndex != 0 && isLand(prevRowIndex, prevColIndex)) // Northwest
        indices = (prevRowIndex, prevColIndex) +: indices
      if (isLand(prevRowIndex, colIndex)) // North
        indices = (prevRowIndex, colIndex) +: indices
      if (colIndex != n && isLand(prevRowIndex, nextColIndex)) // Northeast
        indices = (prevRowIndex, nextColIndex) +: indices
    }
    if (colIndex != n && isLand(rowIndex, nextColIndex)) // East
      indices = (rowIndex, nextColIndex) +: indices
    if (colIndex != 0 && isLand(rowIndex, prevColIndex)) // West
      indices = (rowIndex, prevColIndex) +: indices
    if (rowIndex != m) {
      if (colIndex != n && isLand(nextRowIndex, nextColIndex)) // Southeast
        indices = (nextRowIndex, nextColIndex) +: indices
      if (isLand(nextRowIndex, colIndex)) // South
        indices = (nextRowIndex, colIndex) +: indices
      if (colIndex != 0 && isLand(nextRowIndex, prevColIndex)) // Southwest
        indices = (nextRowIndex, prevColIndex) +: indices
    }
    indices
  }

  private def isLand(rowIndex: Int, colIndex: Int)(implicit area: Seq[Seq[Int]]): Boolean =
    area(rowIndex)(colIndex) != 0
}
