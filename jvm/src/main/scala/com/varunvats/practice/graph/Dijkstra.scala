package com.varunvats.practice.graph

import scala.annotation.tailrec
import scala.collection.mutable

object Dijkstra {

  def search(graph: Map[Int, Seq[Edge]], start: Int, goal: Int): (Seq[Int], Option[Double]) = {
    val cameFrom = mutable.Map.empty[Int, Int]
    val costSoFar = mutable.Map[Int, Double](start -> 0)
    val frontier = mutable.PriorityQueue(start)(Ordering.by { node => -costSoFar(node) })

    while (frontier.nonEmpty) {
      val current = frontier.dequeue()
      graph.get(current).foreach { edges =>
        edges.foreach { case Edge(dst, edgeCost) =>
          val newCost = costSoFar(current) + edgeCost
          if (!costSoFar.contains(dst) || newCost < costSoFar(dst)) {
            cameFrom(dst) = current
            costSoFar(dst) = newCost
            frontier += dst
          }
        }
      }
    }

    costSoFar.get(goal).fold((Seq.empty[Int], None: Option[Double])) { cost =>
      val path = createPath(cameFrom, Seq(goal))
      (path, Some(cost))
    }
  }

  case class Edge(dst: Int, cost: Double) {
    require(cost >= 0, "Cost must be greater than or equal to zero.")
  }

  @tailrec
  private def createPath(cameFrom: mutable.Map[Int, Int], path: Seq[Int]): Seq[Int] = {
    val parentO = cameFrom.get(path.head)
    parentO match {
      case None => path
      case Some(parent) => createPath(cameFrom, parent +: path)
    }
  }

}


