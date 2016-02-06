package com.varunvats.practice.stack

import java.util.{Stack => JStack}

import com.varunvats.practice.sorting.UnitSpec

class SortStackSpec extends UnitSpec {

  "SortStack" should {

    "not complain when asked to sort a null stack" in {
      SortStack.sort(null)
    }

    "sort an empty stack" in {
      val stack = new JStack[Integer]
      SortStack.sort(stack)
      stack.isEmpty shouldBe true
    }

    "sort a stack containing a single element" in {
      val stack = createStack(9)
      SortStack.sort(stack)
      val expectedStack = createStack(9)
      stack shouldBe expectedStack
    }

    "sort a stack containing two elements in sorted order" in {
      val stack = createStack(121, 9)
      SortStack.sort(stack)
      val expectedStack = createStack(121, 9)
      stack shouldBe expectedStack
    }

    "sort a stack containing two elements in unsorted order" in {
      val stack = createStack(9, 121)
      SortStack.sort(stack)
      val expectedStack = createStack(121, 9)
      stack shouldBe expectedStack
    }

    "sort a stack containing three elements in sorted order" in {
      val stack = createStack(191, 56, 2)
      SortStack.sort(stack)
      val expectedStack = createStack(191, 56, 2)
      stack shouldBe expectedStack
    }

    "sort a stack containing three elements in reverse sorted order" in {
      val stack = createStack(2, 56, 191)
      SortStack.sort(stack)
      val expectedStack = createStack(191, 56, 2)
      stack shouldBe expectedStack
    }

    "sort a stack containing seven elements in unsorted (random) order" in {
      val stack = createStack(156, 251, 99, 1, 0, 33, 1001)
      SortStack.sort(stack)
      val expectedStack = createStack(1001, 251, 156, 99, 33, 1, 0)
      stack shouldBe expectedStack
    }
  }

  private def createStack(elems: Integer*): JStack[Integer] = {
    val stack = new JStack[Integer]
    elems.foreach(stack.push)
    stack
  }
}
