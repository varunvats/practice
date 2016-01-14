package com.varunvats.practice

package object skyline {

  case class Building(risingEdgePos: Double, fallingEdgePos: Double, height: Double) {
    require(risingEdgePos < fallingEdgePos, s"Rising edge must come before falling edge in $this")
    require(height > 0, s"Building must have height greater than zero in $this")
  }

}
