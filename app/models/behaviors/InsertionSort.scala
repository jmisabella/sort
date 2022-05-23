package models.behaviors

import models.classes.SortStepResult
import scala.collection.mutable.ListBuffer

trait InsertionSort extends SortByStep {
  override def sortStep[A](list: List[A], iteration: Int = 0)(implicit o: Ordering[A]): SortStepResult[A] = {
    val lst: ListBuffer[A] = ListBuffer(list: _*)
    val key = list(iteration)
    var j = iteration - 1
    while (j >= 0 && o.gt(lst(j), key)) {
      lst(j + 1) = lst(j)
      j -= 1
    }
    lst(j + 1) = key
    SortStepResult(lst.toList, iteration + 1)
  }
}