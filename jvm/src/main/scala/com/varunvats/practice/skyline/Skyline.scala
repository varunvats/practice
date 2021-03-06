package com.varunvats.practice.skyline

import scala.annotation.tailrec
import scala.collection.mutable.{PriorityQueue => MutPriorityQueue}

object Skyline {

  private val startPoint = (0.0, 0.0)

  final def trace(buildings: List[Building]): List[(Double, Double)] = {
    val sortedBuildings = buildings.sortBy(building => building.risingEdgePos)
    val buildingsAtCursor = MutPriorityQueue.empty(Ordering.by { building: Building => -building.fallingEdgePos })
    trace(List(startPoint), sortedBuildings, buildingsAtCursor)
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
        val newPoints = prependPoints(points, (newX, currMaxY), (newX, newY))
        trace(newPoints, risingEdges.tail, buildingsAtCursor += newBuilding)
      case (Some(oldBuilding), _) =>
        val _ = buildingsAtCursor.dequeue()
        val newX = oldBuilding.fallingEdgePos
        val newY = buildingsAtCursor.foldLeft(Building.minHeight)((currMax, building) => currMax.max(building.height))
        val newPoints = prependPoints(points, (newX, currMaxY), (newX, newY))
        trace(newPoints, risingEdges, buildingsAtCursor)
    }
  }

  private def prependPoints(existingPoints: List[(Double, Double)], newPoints: (Double, Double)*): List[(Double, Double)] =
    newPoints.foldLeft(existingPoints) { (updatedPoints, newPoint) =>
      val lastPoint = updatedPoints.head
      if (newPoint != lastPoint && updatedPoints.length >= 2) {
        val secondLastPoint = updatedPoints(1)
        if (secondLastPoint._1 == lastPoint._1 && lastPoint._1 == newPoint._1)
          (lastPoint._1, newPoint._2) +: updatedPoints.tail
        else if (secondLastPoint._2 == lastPoint._2 && lastPoint._2 == newPoint._2)
          (newPoint._1, lastPoint._2) +: updatedPoints.tail
        else
          newPoint +: updatedPoints
      } else if (newPoint != lastPoint) {
        newPoint +: updatedPoints
      } else {
        updatedPoints
      }
    }
}
