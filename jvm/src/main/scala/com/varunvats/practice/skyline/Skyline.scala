package com.varunvats.practice.skyline

import scala.annotation.tailrec
import scala.collection.mutable.{PriorityQueue => MutPriorityQueue}

object Skyline {

  final def trace(buildings: List[Building]): List[(Double, Double)] = {
    val sortedBuildings = buildings.sortBy(building => building.risingEdgePos)
    val buildingsAtCursor = MutPriorityQueue.empty(Ordering.by { building: Building => -building.fallingEdgePos })
    trace(List((0.0, 0.0)), sortedBuildings, buildingsAtCursor)
  }

  @tailrec
  private def trace(points: List[(Double, Double)],
                    risingEdges: List[Building],
                    buildingsAtCursor: MutPriorityQueue[Building]): List[(Double, Double)] = {
    val currMaxY = points.head._2
    (buildingsAtCursor.headOption, risingEdges.headOption) match {
      case (None, None) =>
        points.reverse
      case (oldBuildingO, Some(newBuilding)) if oldBuildingO.isEmpty || newBuilding.risingEdgePos <= oldBuildingO.get.fallingEdgePos =>
        val newX = newBuilding.risingEdgePos
        val newY = if (newBuilding.height > currMaxY) newBuilding.height else currMaxY
        val bottomLeft = (newX, currMaxY)
        val topLeft = (newX, newY)
        val newPoints1 = if (bottomLeft != points.head) bottomLeft +: points else points
        val newPoints2 = if (topLeft != newPoints1.head) topLeft +: newPoints1 else newPoints1
        trace(newPoints2, risingEdges.tail, buildingsAtCursor += newBuilding)
      case (Some(oldBuilding), _) =>
        val _ = buildingsAtCursor.dequeue()
        val newX = oldBuilding.fallingEdgePos
        val newY = buildingsAtCursor.foldLeft(0.0)((currMin, building) => currMin.min(building.height))
        val topRight = (newX, currMaxY)
        val bottomRight = (newX, newY)
        val newPoints = bottomRight +: topRight +: points
        trace(newPoints, risingEdges, buildingsAtCursor)
    }
  }
}
