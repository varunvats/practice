package com.varunvats.practice.graph

import com.varunvats.practice.graph.Dijkstra.Edge
import com.varunvats.practice.sorting.UnitSpec

class DijkstraSpec extends UnitSpec {

  "Dijkstra search" should {

    "return the path with the least cost" when {

      "the start node doesn't exist in the graph (no path, no cost)" in {
        val graph = Map(
          9 -> Seq(Edge(5, 2), Edge(7, 1)),
          7 -> Seq(Edge(10, 0))
        )
        val (path, cost) = Dijkstra.search(graph, 10000010, 9)
        path shouldBe Nil
        cost shouldBe None
      }

      "the goal node doesn't exist in the graph (no path, no cost)" in {
        val graph = Map(
          9 -> Seq(Edge(5, 2), Edge(7, 1)),
          7 -> Seq(Edge(10, 0))
        )
        val (path, cost) = Dijkstra.search(graph, 9, 10000010)
        path shouldBe Nil
        cost shouldBe None
      }

      "the start and the goal nodes are the same and there is no cycle containing the start node" in {
        val graph = Map(
          9 -> Seq(Edge(5, 2), Edge(7, 1)),
          7 -> Seq(Edge(10, 0))
        )
        val (path, cost) = Dijkstra.search(graph, 9, 9)
        path shouldBe Seq(9)
        cost shouldBe Some(0)
      }

      "the start and goal nodes are same and there is a cycle containing the start node" in {
        val graph = Map(
          9 -> Seq(Edge(5, 2), Edge(7, 1)),
          7 -> Seq(Edge(9, 0))
        )
        val (path, cost) = Dijkstra.search(graph, 9, 9)
        path shouldBe Seq(9)
        cost shouldBe Some(0)
      }

      "there is only one path from start to goal" in {
        val graph = Map(
          9 -> Seq(Edge(5, 2), Edge(7, 1)),
          7 -> Seq(Edge(10, 0)),
          10 -> Seq(Edge(16, 3))
        )
        val (path, cost) = Dijkstra.search(graph, 9, 16)
        path shouldBe Seq(9, 7, 10, 16)
        cost shouldBe Some(1 + 0 + 3)
      }

      "there are multiple paths from start to goal (all paths have different costs)" in {
        val graph = Map(
          9 -> Seq(Edge(5, 2), Edge(7, 500), Edge(16, 1e6)),
          5 -> Seq(Edge(21, 3), Edge(64, 1)),
          7 -> Seq(Edge(10, 0)),
          10 -> Seq(Edge(16, 3)),
          64 -> Seq(Edge(16, 3))
        )
        val (path, cost) = Dijkstra.search(graph, 9, 16)
        path shouldBe Seq(9, 5, 64, 16)
        cost shouldBe Some(6)
      }

      "there are multiple paths from start to goal (two paths have same cost)" in {
        val graph = Map(
          9 -> Seq(Edge(5, 6), Edge(7, 3), Edge(16, 1e6)),
          5 -> Seq(Edge(21, 3), Edge(64, 0)),
          7 -> Seq(Edge(10, 2)),
          10 -> Seq(Edge(16, 1)),
          64 -> Seq(Edge(16, 0))
        )
        val (path, cost) = Dijkstra.search(graph, 9, 16)
        path shouldBe Seq(9, 7, 10, 16)
        cost shouldBe Some(6)
      }
    }
  }
}
