package com.franklinchen

import cats._
import cats.implicits._
import cats.data.State

import scala.language.higherKinds

object Main {

  /**
    3 passes through the input collection.

    Simple and intuitive.
    */
  def subtractAverage3Pass(xs: List[Double]): List[Double] = {
    // Pass 1 is the sum.
    // Pass 2 is the length.
    val average = xs.sum / xs.length

    // Pass 3 is the subtraction.
    xs.map(x => x - average)
  }

  /**
    State information to carry while traversing through input.
    */
  case class Stuff(sum: Double, length: Int)

  /**
    Only 1 pass through the input list!

    Price paid: output is a collection of Later[Double]
    rather than a collection of Double.
    */
  def subtractAverage1Pass(xs: List[Double]): List[Later[Double]] = {
    var cell: (Stuff, List[Later[Double]]) = null

    // Cache to compute only once on demand.
    val average = Later(cell._1.sum / cell._1.length)

    // Tie the recursive knot.
    cell = mapAccumL(xs)(Stuff(0, 0)) { (stuff, x) =>
      (
        Stuff(stuff.sum + x, stuff.length + 1),
        Later(x - average.value)
      )
    }

    cell._2
  }

  /**
    Combine a foldLeft with a map, while carrying some state S
    forward.

    Naive implementation, not tail recursive!
    */
  def mapAccumL[A, B, S](xs: List[A])(state: S)(
      f: (S, A) => (S, B)): (S, List[B]) = {
    def go(zs: List[A], state0: S): (S, List[B]) = zs match {
      case Nil => (state0, Nil)
      case x :: xs => {
        val (state1, y) = f(state0, x)
        val (state2, ys) = go(xs, state1)
        (state2, y :: ys)
      }
    }

    go(xs, state)
  }

  /**
    Only 1 pass through the input collection!

    Generic in the collection by using Traverse and State.
    */
  def subtractAverage1PassGeneric[Collection[_]: Traverse](
      xs: Collection[Double]): Collection[Later[Double]] = {
    var cell: (Stuff, Collection[Later[Double]]) = null

    // Cache to compute only once on demand.
    val average = Later(cell._1.sum / cell._1.length)

    // Tie the recursive knot.
    cell = xs
      .traverseU { x =>
        for {
          _ <- State.modify { stuff: Stuff =>
            Stuff(stuff.sum + x, stuff.length + 1)
          }
        } yield Later(x - average.value)
      }
      .run(Stuff(0, 0))
      .value

    cell._2
  }

  /**
    More elegant, "cheating" by using Scala's built-in "lazy val" construct.

    But this obscures what is really going on.
    */
  def subtractAverage1PassCheat[Collection[_]: Traverse](
      xs: Collection[Double]): Collection[Later[Double]] = {
    lazy val (Stuff(sum: Double, length: Int), result) = {
      // Cache to compute only once on demand.
      lazy val average = sum / length

      xs.traverseU { x =>
          for {
            _ <- State.modify { stuff: Stuff =>
              Stuff(stuff.sum + x, stuff.length + 1)
            }
          } yield Later(x - average)
        }
        .run(Stuff(0, 0))
        .value
    }

    result
  }
}
