package models.behaviors

import models.classes.SortStepResult
import scala.collection.mutable.ListBuffer

trait BubbleSorting extends SortByStep {
  override def sortStep[A](list: List[A], iteration: Int = 0)(implicit o: Ordering[A]): SortStepResult[A] = {
    val lst: ListBuffer[A] = ListBuffer(list: _*)
    for (j <- 0 until list.length - 1) {
      if (o.gt(lst(j), lst(j + 1))) {
        val a = lst(j+1)
        val b = lst(j)
        lst(j) = a
        lst(j+1) = b
      }
    }
    SortStepResult(lst.toList, iteration + 1)
  }
}

