package com.varunvats.practice.graph

import com.varunvats.practice.sorting.UnitSpec

class ItinerarySpec extends UnitSpec {

  val JFK = "JFK"
  val SFO = "SFO"
  val SJC = "SJC"
  val LAX = "LAX"
  val ATL = "ATL"
  val MUC = "MUC"
  val LHR = "LHR"

  "The itinerary" must {

    "not exist" when {
      "only two tickets exist and both originate from the same location" in {
        val tickets = List(JFK -> SJC, JFK -> LAX)
        Itinerary(tickets, JFK) shouldBe Nil
      }

      "it is impossible to cover all the stops in a single trip" in {
        val tickets = List(
          JFK -> SFO,
          SFO -> ATL,
          ATL -> SFO,
          ATL -> JFK)
        Itinerary(tickets, JFK) shouldBe Nil
      }
    }

    "consist of only two stops" when {
      "only one ticket exists" in {
        val tickets = List(JFK -> SJC)
        Itinerary(tickets, JFK) shouldBe List(JFK, SJC)
      }
    }

    "consist of three stops" when {
      "there are two tickets and the first stop is a layover" in {
        val tickets = List(JFK -> SJC, SJC -> LAX)
        Itinerary(tickets, JFK) shouldBe List(JFK, SJC, LAX)
      }

      "the trip is a round-trip to one location" in {
        val tickets = List(JFK -> SJC, SJC -> JFK)
        Itinerary(tickets, JFK) shouldBe List(JFK, SJC, JFK)
      }
    }

    "consist of five stops" when {
      "the trip is a round-trip involving three stops in between" in {
        val tickets = List(
          MUC -> LHR,
          JFK -> MUC,
          SFO -> SJC,
          LHR -> SFO)
        Itinerary(tickets, JFK) shouldBe List(JFK, MUC, LHR, SFO, SJC)
      }
    }

    "consist of six stops and must be the one with the least lexical order" when {
      "the trip consists of five tickets and multiple ways to construct the itinerary exist" in {
        val tickets = List(
          JFK -> SFO,
          JFK -> ATL,
          SFO -> ATL,
          ATL -> JFK,
          ATL -> SFO)
        Itinerary(tickets, JFK) shouldBe List(JFK, ATL, JFK, SFO, ATL, SFO)
      }
    }
  }
}
