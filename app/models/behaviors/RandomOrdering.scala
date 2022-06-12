package models.behaviors

import models.utilities.RNG
import scala.collection.mutable.ListBuffer
import scala.util.Random // used only for default initialization when a seed is not specified

trait RandomOrdering {
  // https://en.wikipedia.org/wiki/Fisher%E2%80%93Yates_shuffle
  // Fisher-Yates shuffle
  def shuffle[A](list: List[A], seed: Int = Random.nextInt()): (List[A], RNG) = {
    val lst: ListBuffer[A] = ListBuffer(list: _*)
    var random: RNG = RNG.RandomSeed(seed)
    var n: Int = list.length
    while (n > 1) {
      n -= 1
      val (k, next): (Int, RNG) = random.boundedPositiveInt(n + 1)
      val value: A = lst(k)
      lst(k) = lst(n)
      lst(n) = value
      random = next 
    }
    (lst.toList, random)
  }
}