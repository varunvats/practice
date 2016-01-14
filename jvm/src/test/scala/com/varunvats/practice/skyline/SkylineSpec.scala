package com.varunvats.practice.skyline

import com.varunvats.practice.sorting.UnitSpec

class SkylineSpec extends UnitSpec {

  "A correct skyline" must {

    "be created for a single building located at (0, 0)" in {
      val buildings = List(Building(0, 6.5, 21.3))
      Skyline.trace(buildings) shouldBe List((0.0, 0.0), (0.0, 21.3), (6.5, 21.3), (6.5, 0))
    }

  }

}
