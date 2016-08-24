package com.franklinchen

import org.specs2._

import cats._
import cats.implicits._

class MainSpec extends Specification { def is = s2"""
  ${`3 pass subtract average from a list of numbers`}
  ${`1 pass subtract average from a list of numbers`}
  ${`1 pass subtract average from a collection of numbers`}
  ${`More elegant 1 pass subtract average from a collection of numbers`}
  """

  val inputList = List(1.0, 2.0, 3.0, 4.0, 5.0)
  val outputList = List(-2.0, -1.0, 0.0, 1.0, 2.0)

  val inputVector = Vector(1.0, 2.0, 3.0, 4.0, 5.0)
  val outputVector = Vector(-2.0, -1.0, 0.0, 1.0, 2.0)

  def `3 pass subtract average from a list of numbers` = {
    Main.subtractAverage3Pass(inputList) ==== outputList
  }

  /** Note the forcing of each lazy element in the list. */
  def `1 pass subtract average from a list of numbers` = {
    Main.subtractAverage1Pass(inputList).map(_.value) ==== outputList
  }

  /** Use a Vector instead of List to illustrate genericity. */
  def `1 pass subtract average from a collection of numbers` = {
    Main.subtractAverage1PassGeneric(inputVector).map(_.value) ==== outputVector
  }

  def `More elegant 1 pass subtract average from a collection of numbers` = {
    Main.subtractAverage1PassCheat(inputList).map(_.value) ==== outputList
  }

}
