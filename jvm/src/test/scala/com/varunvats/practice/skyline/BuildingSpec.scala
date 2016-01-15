package com.varunvats.practice.skyline

import com.varunvats.practice.sorting.UnitSpec

class BuildingSpec extends UnitSpec {

  "A building" must {

    "not be created" when {

      "the rising edge is at the same position as the falling edge" in {
        intercept[IllegalArgumentException] {
          Building(5.3, 5.3, 9.1)
        }
      }

      "the rising edge is after the falling edge" in {
        intercept[IllegalArgumentException] {
          Building(5.3, 3.1, 9.1)
        }
      }

      "its height is equal to the minimum height (0.0)" in {
        intercept[IllegalArgumentException] {
          Building(3.1, 13.3, 0.0)
        }
      }

      "its height is lesser than the minimum height (0.0)" in {
        intercept[IllegalArgumentException] {
          Building(3.1, 13.3, -2.3)
        }
      }
    }

  }

}
