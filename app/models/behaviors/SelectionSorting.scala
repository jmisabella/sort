package models.behaviors

import models.classes.SortStepResult
import scala.collection.mutable.ListBuffer

trait SelectionSorting extends SortByStep {
  override def sortStep[A](list: List[A], iteration: Int = 0)(implicit o: Ordering[A]): SortStepResult[A] = {
    val lst: ListBuffer[A] = ListBuffer(list: _*)
    var minIndex: Int = iteration
    for (j <- (iteration + 1 until list.length)) {
      if (o.lt(list(j), list(minIndex))) {
        minIndex = j
      }
    }
    val minimum: A = list(minIndex)
    lst(minIndex) = list(iteration)
    lst(iteration) = minimum
    SortStepResult(lst.toList, iteration + 1)
  }
}