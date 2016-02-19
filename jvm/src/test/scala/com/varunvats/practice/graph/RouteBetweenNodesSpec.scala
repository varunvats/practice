package com.varunvats.practice.graph

import com.varunvats.practice.sorting.UnitSpec

class RouteBetweenNodesSpec extends UnitSpec {

  val between = afterWord("between")

  "A route" must {
    
    "exist between two nodes" when {
      "the nodes are the same and there is no edge from the node to itself" in {
        val graph = Map(
          1 -> Seq(2),
          2 -> Seq(3, 4)
        )
        RouteBetweenNodes(graph, 2, 2) shouldBe true
      }

      "the second node is reachable from the first" in {
        val graph = Map(
          1 -> Seq(2),
          2 -> Seq(3, 4),
          3 -> Seq(4, 5, 6),
          5 -> Seq(2, 11),
          11 -> Seq(9)
        )
        RouteBetweenNodes(graph, 1, 6) shouldBe true
      }
    }

    "not exist between two nodes" when {
      "the second node is not reachable from the first and the first node is part of a cycle" in {
        val graph = Map(
          1 -> Seq(2),
          2 -> Seq(3, 4),
          3 -> Seq(4, 5, 6),
          5 -> Seq(2, 11),
          11 -> Seq(9)
        )
        RouteBetweenNodes(graph, 2, 1) shouldBe false
      }

      "the first node is reachable from the second but the second is not reachable from the first" in {
        val graph = Map(
          1 -> Seq(2),
          2 -> Seq(3, 4),
          3 -> Seq(4, 5, 6),
          5 -> Seq(2, 11),
          11 -> Seq(9)
        )
        RouteBetweenNodes(graph, 9, 2) shouldBe false
      }

      "the second node is not reachable from the first" in {
        val graph = Map(
          1 -> Seq(2, 21, 31),
          2 -> Seq(3, 4),
          3 -> Seq(4, 5, 6),
          5 -> Seq(2, 11),
          11 -> Seq(9),
          41 -> Seq(1, 23),
          23 -> Seq(2)
        )
        RouteBetweenNodes(graph, 1, 23) shouldBe false
      }
    }
  }
}
