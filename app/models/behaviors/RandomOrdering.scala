package models.behaviors

import models.utilities.RNG
import scala.collection.mutable.ListBuffer

trait RandomOrdering {
  // https://en.wikipedia.org/wiki/Fisher%E2%80%93Yates_shuffle
  // Fisher-Yates shuffle
  // TODO: test
  def shuffle[A](list: List[A], seed: RNG): (List[A], RNG) = {
    val lst: ListBuffer[A] = ListBuffer(list: _*)
    var random: RNG = seed 
    var n: Int = list.length
    while (n > 1) {
      n -= 1
      val (k, nextSeed): (Int, RNG) = random.boundedPositiveInt(n + 1)
      val value: A = lst(k)
      lst(k) = lst(n)
      lst(n) = value
      random = nextSeed
    }
    (lst.toList, random)
  }
}