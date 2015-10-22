package com.franklinchen

import cats._
import cats.data.Streaming

object Main {
  // 2 passes
  def subtractAverage2Pass(xs: List[Double]): List[Double] = {
    // Pass 1
    val average = xs.sum / xs.length

    // Pass 2
    xs map (_ - average)
  }

  case class Stuff(sum: Double, length: Int)

  // 1 pass.
  //
  // The price to pay is that we end up with a list of lazy values.
  def subtractAverage1Pass(xs: List[Double]): List[Later[Double]] = {
    lazy val (
      Stuff(sum: Double, length: Int),
      result: List[Later[Double]]
    ) = {
      lazy val average = sum / length

      mapAccumL(xs)(Stuff(0, 0)) { (stuff, x) =>
        (
          Stuff(stuff.sum + x, stuff.length + 1),
          Later(x - average)
        )
      }
    }

    result
  }

  // Combine a foldLeft with a map.
  //
  // Note: can generalize to any Traverse using an internal State monad.
  def mapAccumL[A, B, Acc](xs: List[A])(acc: Acc)(f: (Acc, A) => (Acc, B))
      : (Acc, List[B]) = {
    def go(zs: List[A], acc: Acc): (Acc, List[B]) = zs match {
      case Nil => (acc, Nil)
      case x::xs => {
        val (acc1, y) = f(acc, x)
        val (acc2, ys) = go(xs, acc1)
        (acc2, y::ys)
      }
    }

    go(xs, acc)
  }
}
