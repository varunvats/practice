package com.varunvats.practice.graph

import com.varunvats.practice.graph.BuildOrder.NoValidBuildOrderException
import com.varunvats.practice.sorting.UnitSpec

class BuildOrderSpec extends UnitSpec {

  "The build order creator" must {
    "create the correct build order" when {
      "none of the projects have any dependencies and no dependencies are specified" in {
        val projects = List('A', 'Z', 'P')
        BuildOrder(projects, Nil) shouldBe Seq('A', 'Z', 'P')
      }

      "none of the projects have any dependencies but the dependency list contains unrelated projects" in {
        val projects = List('A')
        val dependencies = List('Z' -> 'P', 'X' -> 'Y')
        BuildOrder(projects, dependencies) shouldBe Seq('A')
      }

      "there is only one project and it has only one dependency" in {
        val projects = List('A')
        val dependencies = List('A' -> 'P')
        BuildOrder(projects, dependencies) shouldBe Seq('P', 'A')
      }

      "the are two projects and both have one dependency each" in {
        val projects = List('A', 'B')
        val dependencies = List('A' -> 'P', 'B' -> 'X')
        BuildOrder(projects, dependencies) shouldBe Seq('P', 'A', 'X', 'B')
      }

      "there are two projects and they have the same dependency" in {
        val projects = List('A', 'B')
        val dependencies = List('A' -> 'P', 'B' -> 'P')
        BuildOrder(projects, dependencies) shouldBe Seq('P', 'A', 'B')
      }

      "there are two projects and one project's dependency has another dependency" in {
        val projects = List('A', 'B')
        val dependencies = List('A' -> 'P', 'P' -> 'C', 'B' -> 'X')
        BuildOrder(projects, dependencies) shouldBe Seq('C', 'P', 'A', 'X', 'B')
      }

      "the are two projects and each has multiple dependencies only one level deep" in {
        val projects = List('A', 'B')
        val dependencies = List(
          'A' -> 'P',
          'A' -> 'Q',
          'A' -> 'R',
          'B' -> 'F',
          'B' -> 'G',
          'B' -> 'H'
        )
        val expectedBuildOrder = Seq('R', 'Q', 'P', 'A', 'H', 'G', 'F', 'B')
        BuildOrder(projects, dependencies) shouldBe expectedBuildOrder
      }

      "there are two projects and each has multiple dependencies, two levels deep" in {
        val projects = List('A', 'B')
        val dependencies = List(
          'A' -> 'P',
          'A' -> 'Q',
          'A' -> 'R',
          'P' -> 'T',
          'P' -> 'U',
          'R' -> 'S',
          'B' -> 'F',
          'B' -> 'G',
          'B' -> 'H',
          'H' -> 'I'
        )
        val expectedBuildOrder = Seq('S', 'R', 'Q', 'U', 'T', 'P', 'A', 'I', 'H', 'G', 'F', 'B')
        BuildOrder(projects, dependencies) shouldBe expectedBuildOrder
      }

      "there are two projects and one of first project's many dependencies is the other project" in {
        val projects = List('A', 'B')
        val dependencies = List(
          'A' -> 'P',
          'A' -> 'B',
          'P' -> 'Q',
          'B' -> 'R',
          'B' -> 'U',
          'R' -> 'L'
        )
        val expectedBuildOrder = Seq('U', 'L', 'R', 'B', 'Q', 'P', 'A')
        BuildOrder(projects, dependencies) shouldBe expectedBuildOrder
      }

      "two projects share the same dependency and that dependency has multiple multi-level dependencies" in {
        val projects = List('A', 'B')
        val dependencies = List(
          'A' -> 'P',
          'B' -> 'P',
          'P' -> 'Q',
          'P' -> 'R',
          'P' -> 'S',
          'Q' -> 'T',
          'Q' -> 'U',
          'U' -> 'M',
          'R' -> 'N'
        )
        val expectedBuildOrder = Seq('S', 'N', 'R', 'M', 'U', 'T', 'Q', 'P', 'A', 'B')
        BuildOrder(projects, dependencies) shouldBe expectedBuildOrder
      }

      "there is a diamond dependency" in {
        val projects = List('A', 'B')
        val dependencies = List(
          'A' -> 'P',
          'A' -> 'Q',
          'P' -> 'R',
          'Q' -> 'R',
          'R' -> 'S',
          'P' -> 'O',
          'B' -> 'O'
        )
        val expectedBuildOrder = Seq('S', 'R', 'Q', 'O', 'P', 'A', 'B')
        BuildOrder(projects, dependencies) shouldBe expectedBuildOrder
      }

      "there are two adjacent diamond dependencies that share two dependencies" in {
        val projects = List('A', 'B')
        val dependencies = List(
          'A' -> 'P',
          'A' -> 'Q',
          'P' -> 'R',
          'Q' -> 'R',
          'B' -> 'P',
          'B' -> 'S',
          'R' -> 'T',
          'S' -> 'T'
        )
        val expectedBuildOrder = Seq('T', 'R', 'Q', 'P', 'A', 'S', 'B')
        BuildOrder(projects, dependencies) shouldBe expectedBuildOrder
      }

      "there is a cyclic dependency but none of the projects in the cyclic dependency need to be built" in {
        val projects = List('A')
        val dependencies = List(
          'A' -> 'D',
          'P' -> 'Q',
          'Q' -> 'R',
          'R' -> 'P'
        )
        val expectedBuildOrder = Seq('D', 'A')
        BuildOrder(projects, dependencies) shouldBe expectedBuildOrder
      }
    }

    "throw an exception" when {
      "there is only one project and it depends on itself" in {
        val projects = List('A')
        val dependencies = List('A' -> 'A')
        intercept[NoValidBuildOrderException] {
          BuildOrder(projects, dependencies)
        }
      }

      "there is a project with a cyclic dependency that involves more than one project" in {
        val projects = List('A', 'B')
        val dependencies = List(
          'A' -> 'P',
          'P' -> 'B',
          'B' -> 'Q',
          'Q' -> 'P',
          'B' -> 'L'
        )
        intercept[NoValidBuildOrderException] {
          BuildOrder(projects, dependencies)
        }
      }

      "there are two projects and both depend on each other" in {
        val projects = List('A', 'B')
        val dependencies = List('A' -> 'B', 'B' -> 'A')
        intercept[NoValidBuildOrderException] {
          BuildOrder(projects, dependencies)
        }
      }
    }
  }

}
