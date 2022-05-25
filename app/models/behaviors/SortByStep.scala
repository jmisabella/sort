package models.behaviors

import models.classes.{ SortStepResult, HistoricalList }

trait SortByStep extends Sorting {

  def sortStep[A](list: List[A], iteration: Int = 0)(implicit o: Ordering[A]): SortStepResult[A]

  override def sort[A](list: List[A])(implicit o: Ordering[A]): HistoricalList[A] = {
    var result: SortStepResult[A] = SortStepResult(list)
    var steps: List[List[A]] = Nil
    while (!isSorted(result.list)) {
      result = sortStep(result.list, result.iteration)
      steps = steps ++ List(result.list)
    }
    HistoricalList(steps, list)
  }
}
