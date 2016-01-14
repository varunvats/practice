package com.varunvats.practice

package object skyline {

  object Building {
    val minHeight = 0.0
  }

  case class Building(risingEdgePos: Double, fallingEdgePos: Double, height: Double) {

    import Building.minHeight

    require(risingEdgePos < fallingEdgePos, s"Rising edge must come before falling edge in $this")
    require(height > minHeight, s"Building must have height greater than $minHeight in $this")
  }

}
