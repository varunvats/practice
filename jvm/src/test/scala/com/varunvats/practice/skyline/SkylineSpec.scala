package com.varunvats.practice.skyline

import com.varunvats.practice.sorting.UnitSpec

class SkylineSpec extends UnitSpec {

  private val start = (0.0, 0.0)

  "A correct skyline" must {

    "be created" when {

      "no buildings exist" in {
        val buildings = Nil
        Skyline.trace(buildings) shouldBe List(start)
      }

      "one building is located at the start point" in {
        val buildings = List(Building(0, 6.5, 21.3))
        val expectedTrace = List(start, (0.0, 21.3), (6.5, 21.3), (6.5, 0.0))
        Skyline.trace(buildings) shouldBe expectedTrace
      }

      "one building is located at a distance from the start point" in {
        val buildings = List(Building(11.1, 19.5, 21.3))
        val expectedTrace = List(start, (11.1, 0.0), (11.1, 21.3), (19.5, 21.3), (19.5, 0.0))
        Skyline.trace(buildings) shouldBe expectedTrace
      }

      "two buildings are separated by a distance (no overlap)" in {
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

      "a portion of two buildings overlaps and the second building is taller than the first" in {
        val buildings = List(Building(11.1, 19.5, 15.9), Building(14.3, 35.1, 40.3))
        val expectedTrace = List(start, (11.1, 0.0), (11.1, 15.9), (14.3, 15.9), (14.3, 40.3), (35.1, 40.3), (35.1, 0.0))
        Skyline.trace(buildings) shouldBe expectedTrace
      }

      "the second building starts where the first ends and the first building is taller" in {
        val buildings = List(Building(11.1, 19.5, 40.3), Building(19.5, 35.1, 12.7))
        val expectedTrace = List(start, (11.1, 0.0), (11.1, 40.3), (19.5, 40.3), (19.5, 12.7), (35.1, 12.7), (35.1, 0.0))
        Skyline.trace(buildings) shouldBe expectedTrace
      }

      "the second building starts where the first ends and the second building is taller" in {
        val buildings = List(Building(11.1, 19.5, 12.7), Building(19.5, 35.1, 40.3))
        val expectedTrace = List(start, (11.1, 0.0), (11.1, 12.7), (19.5, 12.7), (19.5, 40.3), (35.1, 40.3), (35.1, 0.0))
        Skyline.trace(buildings) shouldBe expectedTrace
      }

      "one building is completely overshadowed by the other" in {
        val buildings = List(Building(11.1, 73.5, 40.3), Building(20.9, 41.1, 12.7))
        val expectedTrace = List(start, (11.1, 0.0), (11.1, 40.3), (73.5, 40.3), (73.5, 0.0))
        Skyline.trace(buildings) shouldBe expectedTrace
      }

      "two buildings start at the same location and have the same width and height" in {
        val buildings = List(Building(11.1, 73.5, 40.3), Building(11.1, 73.5, 40.3))
        val expectedTrace = List(start, (11.1, 0.0), (11.1, 40.3), (73.5, 40.3), (73.5, 0.0))
        Skyline.trace(buildings) shouldBe expectedTrace
      }

      "two buildings start at the same location, but one is taller and the other is wider" in {
        val buildings = List(Building(11.1, 73.5, 40.3), Building(11.1, 21.5, 100.3))
        val expectedTrace = List(start, (11.1, 0.0), (11.1, 100.3), (21.5, 100.3), (21.5, 40.3), (73.5, 40.3), (73.5, 0.0))
        Skyline.trace(buildings) shouldBe expectedTrace
      }

      "two buildings end at the same location, but one is taller and the other is wider" in {
        val buildings = List(Building(11.1, 73.5, 40.3), Building(21.5, 73.5, 100.3))
        val expectedTrace = List(start, (11.1, 0.0), (11.1, 40.3), (21.5, 40.3), (21.5, 100.3), (73.5, 100.3), (73.5, 0.0))
        Skyline.trace(buildings) shouldBe expectedTrace
      }
    }
  }
}
