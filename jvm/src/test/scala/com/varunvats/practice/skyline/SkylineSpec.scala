package com.varunvats.practice.skyline

import com.varunvats.practice.sorting.UnitSpec

class SkylineSpec extends UnitSpec {

  "A correct skyline" must {

    "be created for a single building located at x = 0.0" in {
      val buildings = List(Building(0, 6.5, 21.3))
      Skyline.trace(buildings) shouldBe List((0.0, 0.0), (0.0, 21.3), (6.5, 21.3), (6.5, 0))
    }

    "be created for a single building located at x = 11.1" in {
      val buildings = List(Building(11.1, 19.5, 21.3))
      Skyline.trace(buildings) shouldBe List((0.0, 0.0), (11.1, 0.0), (11.1, 21.3), (19.5, 21.3), (19.5, 0))
    }
  }
}
