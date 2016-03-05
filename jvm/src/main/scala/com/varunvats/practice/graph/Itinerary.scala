package com.varunvats.practice.graph

import scala.collection.mutable

object Itinerary {

  type Ticket = (String, String)

  def apply(tickets: List[Ticket], start: String): Seq[String] = {
    val graph = createGraph(tickets)
    val itineraries = getItineraries(graph, start, tickets, Seq(start))
    if (itineraries.isEmpty)
      return Nil
    itineraries.reduce { (minSoFar, curr) =>
      require(minSoFar.length == curr.length, "Two itineraries must have the same number of stops.")
      if (curr.toString() < minSoFar.toString())
        curr
      else
        minSoFar
    }
  }

  private def getItineraries(graph: Map[String, List[String]], from: String,
                             tickets: List[Ticket], itinerary: Seq[String]): List[Seq[String]] = {
    if (itineraryIsComplete(itinerary, tickets))
      return List(itinerary.reverse)
    graph(from).flatMap { to =>
      if (ticketAvailable((from, to), tickets, itinerary))
        getItineraries(graph, to, tickets, to +: itinerary)
      else
        Nil
    }
  }

  private def ticketAvailable(ticket: Ticket, tickets: List[Ticket],
                              itinerary: Seq[String]): Boolean = {
    val numTicketsAvailable = tickets.count(_ == ticket)
    val numTicketsUsed = itinerary.sliding(2).count(t => t.head == ticket._1 && t(1) == ticket._2)
    numTicketsAvailable > numTicketsUsed
  }

  private def itineraryIsComplete(itinerary: Seq[String], tickets: List[Ticket]): Boolean =
    itinerary.length == tickets.length + 1

  private def createGraph(tickets: List[Ticket]): Map[String, List[String]] = {
    val graph = mutable.Map.empty[String, List[String]]
    tickets foreach { case (from, to) =>
      if (!graph.contains(from))
        graph += (from -> List(to))
      else
        graph(from) = to +: graph(from)
    }
    graph.toMap
  }

}
