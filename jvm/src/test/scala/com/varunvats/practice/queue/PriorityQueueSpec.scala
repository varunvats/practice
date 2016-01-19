package com.varunvats.practice.queue

import com.varunvats.practice.sorting.UnitSpec

class PriorityQueueSpec extends UnitSpec {

  "The priority queue" when {

    "empty" should {
      "throw an exception when asked for the head element" in {
        intercept[NoSuchElementException] {
          PriorityQueue[Int]().head
        }
      }

      "throw an exception when dequeue'd" in {
        intercept[NoSuchElementException] {
          PriorityQueue[Int]().dequeue
        }
      }

      "have a length of 0" in {
        PriorityQueue[Int]().length shouldBe 0
      }

      "be empty (test isEmpty)" in {
        PriorityQueue[Int]().isEmpty shouldBe true
      }

      "have a length of 1 when another element is enqueued" in {
        val pq = PriorityQueue[Int]()
        pq += 5
        pq.length shouldBe 1
      }

      "not be empty when another element is enqueued" in {
        val pq = PriorityQueue[Int]()
        pq += 5
        pq.isEmpty shouldBe false
      }
    }

    "containing one element" should {
      "not be empty" in {
        PriorityQueue(5).isEmpty shouldBe false
      }

      "have length 1" in {
        PriorityQueue(101).length shouldBe 1
      }

      "have a length of 2 when another element is enqueued" in {
        val pq = PriorityQueue(7)
        pq += 99
        pq.length shouldBe 2
      }

      "become empty when dequeue'd" in {
        val pq = PriorityQueue(57)
        pq.dequeue
        pq.isEmpty shouldBe true
      }

      "have length 0 when dequeue'd" in {
        val pq = PriorityQueue(57)
        pq.dequeue
        pq.length shouldBe 0
      }

      "return the element when asked for the head element without removing it from the queue" in {
        val pq = PriorityQueue(5)
        pq.head shouldBe 5
        pq.length shouldBe 1
      }

      "remove the element from the queue and return it when dequeue'd and the queue should become empty" in {
        val pq = PriorityQueue(101)
        val elem = pq.dequeue
        elem shouldBe 101
        pq.isEmpty shouldBe true
      }
    }

    "containing multiple elements" should {
      "return the correct length after every enqueue" in {
        val pq = PriorityQueue(12, 1, 37)
        pq.length shouldBe 3
        pq += 101
        pq.length shouldBe 4
        pq += 53 += 77
        pq.length shouldBe 6
      }

      "return the correct length after every dequeue" in {
        val pq = PriorityQueue(12, 1, 37)
        pq += 101
        pq += 53 += 103
        pq.length shouldBe 6
        pq.dequeue
        pq.length shouldBe 5
        pq.dequeue
        pq.length shouldBe 4
        pq.dequeue
        pq.length shouldBe 3
        pq.dequeue
        pq.length shouldBe 2
        pq.dequeue
        pq.length shouldBe 1
        pq.dequeue
        pq.length shouldBe 0
      }

      "promote element with the highest priority to the top of the queue" in {
        val pq = PriorityQueue(12, 1, 37)
        pq.dequeue shouldBe 37
        pq += 101
        pq.dequeue shouldBe 101
      }

      "dequeue elements in decreasing priority order" in {
        val pq = PriorityQueue(12, 1, 37)
        pq.dequeue shouldBe 37
        pq.dequeue shouldBe 12
        pq += 101 += 0
        pq.dequeue shouldBe 101
        pq.dequeue shouldBe 1
        pq += 53
        pq.dequeue shouldBe 53
        pq.dequeue shouldBe 0
      }

      "become empty when all its elements are dequeue'd" in {
        val pq = PriorityQueue(12, 1, 37)
        pq += 101 += 53 += 103
        for (i <- 1 to 6)
          pq.dequeue
        pq.isEmpty shouldBe true
      }

      "dequeue elements correctly when they all have the same priority" in {
        val pq = PriorityQueue(5, 5, 5, 5, 5)
        for (i <- 1 to 5)
          pq.dequeue shouldBe 5
        pq.isEmpty shouldBe true
      }

      "dequeue elements in the correct order when they are inserted in increasing priority order" in {
        val pq = PriorityQueue(1, 2, 3, 4, 5)
        pq.dequeue shouldBe 5
        pq.dequeue shouldBe 4
        pq.dequeue shouldBe 3
        pq.dequeue shouldBe 2
        pq.dequeue shouldBe 1
        pq.isEmpty shouldBe true
      }

      "deque elements in the correct order when they are inserted in decreasing priority order" in {
        val pq = PriorityQueue(5, 4, 3, 2, 1)
        pq.dequeue shouldBe 5
        pq.dequeue shouldBe 4
        pq.dequeue shouldBe 3
        pq.dequeue shouldBe 2
        pq.dequeue shouldBe 1
        pq.isEmpty shouldBe true
      }
    }
  }
}
