package com.varunvats.practice.sorting

import org.scalatest.{Matchers, WordSpec}

abstract class UnitSpec extends WordSpec with Matchers {

  protected final def array(numbers: Double*) = Array(numbers: _*).map(Double.box)

}
