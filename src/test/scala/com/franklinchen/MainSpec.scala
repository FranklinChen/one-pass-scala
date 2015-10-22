package com.franklinchen

import org.specs2._

import cats._
import cats.data.Streaming

class MainSpec extends Specification { def is = s2"""
  ${`2 pass subtract average from a list of numbers`}
  ${`1 pass subtract average from a list of numbers`}
  """

  val inputList = List(1.0, 2.0, 3.0, 4.0, 5.0)
  val outputList = List(-2.0, -1.0, 0.0, 1.0, 2.0)

  def `2 pass subtract average from a list of numbers` = {
    Main.subtractAverage2Pass(inputList) ==== outputList
  }

  // Note the forcing of each lazy element in the list.
  def `1 pass subtract average from a list of numbers` = {
    Main.subtractAverage1Pass(inputList).map(_.value) ==== outputList
  }
}
