package models.behaviors

import models.classes.HistoricalList
import scala.language.postfixOps
import scala.annotation.tailrec

trait QuickSorting extends Sorting {

  def quicksort[A](xs: HistoricalList[A])(implicit o: Ordering[A]): HistoricalList[A] = {

    @tailrec
    def qsort(todo: List[HistoricalList[A]], done: HistoricalList[A]): HistoricalList[A] = todo match {
      case Nil => done
      case hxs :: rest => hxs match {
        case Nil => qsort(rest, done)
        case _ => {
          hxs.currentStep match {
            case Nil => done 
            case x :: xrest =>
              val (ls, rs): (List[A], List[A]) = (xrest partition(o.lt(x,_)))
                if (ls.isEmpty) {
                  if (rs.isEmpty)
                    // qsort(rest, HistoricalList(x :: done.currentStep, List(x :: done.currentStep) ++ done.previousSteps))
                    qsort(rest, HistoricalList(x :: done.currentStep, done))
                  else 
                    // qsort(List(HistoricalList(rs)) ++ rest, HistoricalList(x :: done.currentStep, List(x :: done.currentStep) ++ done.previousSteps))
                    qsort(List(HistoricalList(rs)) ++ rest, HistoricalList(x :: done.currentStep, done))
                }
                else 
                  qsort(List(HistoricalList(ls ++ List(x) ++ rs)) ++ rest, done) // TODO: make sure this line preserves history
          }
        }
      }
    }

    qsort(List(xs), HistoricalList(Nil))
  }
  
  override def sort[A](list: List[A], preserveSteps: Boolean)(implicit o: Ordering[A]): HistoricalList[A] = { 
    quicksort(HistoricalList(list)).copy(original = list)
  }
}