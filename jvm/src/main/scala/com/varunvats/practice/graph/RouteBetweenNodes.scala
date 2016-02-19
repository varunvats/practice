package com.varunvats.practice.graph

import scala.collection.mutable

object RouteBetweenNodes {

  def apply[T](graph: Map[T, Seq[T]], start: T, goal: T): Boolean =
    apply(graph, start, goal, mutable.Set(start))

  private def apply[T](graph: Map[T, Seq[T]], start: T, goal: T, visited: mutable.Set[T]): Boolean = {
    if (start == goal)
      true
    else if (!graph.contains(start))
      false
    else
      graph(start).exists { t =>
        !visited(t) && apply(graph, t, goal, visited += t)
      }
  }

}
