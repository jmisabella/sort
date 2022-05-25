package models.behaviors

import models.classes.HistoricalList

trait Sorting {

  def sort[A](list: List[A])(implicit o: Ordering[A]): HistoricalList[A]

  def isSorted[A](s: Seq[A])(implicit o: Ordering[A]): Boolean = s match {
    case Nil => true // empty list is considered sorted
    case Seq(_) => true // single element is considered sorted
    case _ => s.sliding(2).forall { case Seq(x, y) => o.lteq(x, y) }
  }
}