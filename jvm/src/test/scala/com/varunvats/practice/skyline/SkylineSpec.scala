package com.varunvats.practice.skyline

import com.varunvats.practice.sorting.UnitSpec

class SkylineSpec extends UnitSpec {

  private val start = (0.0, 0.0)

  "A correct skyline" must {

    "be created" when {

      "a single building located at x = 0.0" in {
        val buildings = List(Building(0, 6.5, 21.3))
        Skyline.trace(buildings) shouldBe List(start, (0.0, 21.3), (6.5, 21.3), (6.5, 0))
      }

      "a single building located at x = 11.1" in {
        val buildings = List(Building(11.1, 19.5, 21.3))
        Skyline.trace(buildings) shouldBe List(start, (11.1, 0.0), (11.1, 21.3), (19.5, 21.3), (19.5, 0.0))
      }

      "two buildings don't overlap at all" in {
        val buildings = List(Building(11.1, 19.5, 21.3), Building(29.3, 35.1, 15.9))
        val firstBuildingTrace = List(start, (11.1, 0.0), (11.1, 21.3), (19.5, 21.3), (19.5, 0.0))
        val secondBuildingTrace = List((29.3, 0.0), (29.3, 15.9), (35.1, 15.9), (35.1, 0.0))
        Skyline.trace(buildings) shouldBe firstBuildingTrace ++ secondBuildingTrace
      }

      "a portion of two buildings overlaps and the first building is taller than the second" in {
        val buildings = List(Building(11.1, 19.5, 40.3), Building(14.3, 35.1, 15.9))
        val expectedTrace = List(start, (11.1, 0.0), (11.1, 40.3), (19.5, 40.3), (19.5, 15.9), (35.1, 15.9), (35.1, 0.0))
        Skyline.trace(buildings) shouldBe expectedTrace
      }

      "a portion of two buildings overlaps and the second building is taller than the first" in pending

      "the second building starts at the point at which the first ends and the first building is taller" in pending

      "the second building starts at the point at which the first ends and the second building is taller" in pending

      "one building is completely overshadowed by the other" in pending

      "two buildings share the same start point and have the same width and height" in pending

      "two buildings share the same start point, but one is taller and wider than the other" in pending

      "two buildings share the same end point, but one is taller and wider than the other" in pending
    }
  }
}
