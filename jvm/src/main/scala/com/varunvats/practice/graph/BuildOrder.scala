package com.varunvats.practice.graph

import scala.collection.mutable

object BuildOrder {

  private sealed trait State

  private object Building extends State

  private object Built extends State

  def apply[T](projects: List[T], dependencies: List[(T, T)]): Seq[T] = {
    val dependencyGraph = createDependencyGraph(dependencies)
    val projectStates = mutable.Map.empty[T, State]
    projects.foldLeft(mutable.ListBuffer.empty[T]) { (buildOrder, project) =>
      projectStates.get(project).fold(apply(project, dependencyGraph, projectStates, buildOrder)) {
        case Building => // Sanity check.
          val msg = s"Project $project is in illegal state ($Building)."
          throw new IllegalStateException(msg)
        case Built =>
      }
      buildOrder
    }
  }

  private def apply[T](project: T,
                       dependencyGraph: Map[T, List[T]],
                       projectStates: mutable.Map[T, State],
                       buildOrder: mutable.ListBuffer[T]): Unit = {
    projectStates += (project -> Building)
    dependencyGraph.get(project).foreach { dependencies =>
      dependencies.foreach { dependency =>
        if (!projectStates.contains(dependency))
          apply(dependency, dependencyGraph, projectStates, buildOrder)
        else
          projectStates(dependency) match {
            case Building =>
              val msg = s"Project $dependency has a circular dependency on itself via project $project"
              throw NoValidBuildOrderException(msg)
            case Built =>
          }
      }
    }
    projectStates(project) = Built
    buildOrder += project
  }

  private def createDependencyGraph[T](dependencies: List[(T, T)]): Map[T, List[T]] = {
    val emptyGraph = mutable.Map.empty[T, List[T]]
    val graph = dependencies.foldLeft(emptyGraph) { case (tmpGraph, (dependent, dependency)) =>
      if (tmpGraph.contains(dependent))
        tmpGraph(dependent) = dependency +: tmpGraph(dependent)
      else
        tmpGraph += (dependent -> List(dependency))
      tmpGraph
    }
    graph.toMap
  }

  case class NoValidBuildOrderException(msg: String) extends RuntimeException(msg)

}
