package models.behaviors

import models.classes.HistoricalList

trait Sorting {

  def sort[A](list: List[A], preserveSteps: Boolean = false)(implicit o: Ordering[A]): HistoricalList[A]

  def isSorted[A](s: Seq[A])(implicit ord2: Ordering[A]): Boolean = s match {
    case Nil => true // empty list is considered sorted
    case Seq(_) => true // single element is considered sorted
    case _ => s.sliding(2).forall { case Seq(x, y) => ord2.lteq(x, y) }
  }
}