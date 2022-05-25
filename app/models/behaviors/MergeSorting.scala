package models.behaviors

import models.classes.HistoricalList
import scala.annotation.tailrec

trait MergeSorting extends Sorting {

  @tailrec
  final def merge[A](seq1: HistoricalList[A], seq2: HistoricalList[A], acc: HistoricalList[A])(implicit o: Ordering[A]): HistoricalList[A] = { 
    (seq1.currentStep, seq2.currentStep) match {
      case (Nil, _) => HistoricalList(acc.currentStep ++ seq2.currentStep, acc.previousSteps)
      case (_, Nil) => HistoricalList(acc.currentStep ++ seq1.currentStep, acc.previousSteps)
      case (x::xs, y::ys) =>
        if(o.lt(x, y)) merge(HistoricalList(xs, seq1.previousSteps) ,seq2, HistoricalList(acc.currentStep :+ x, acc))
        else merge(seq1,HistoricalList(ys, seq2.previousSteps), HistoricalList(acc.currentStep :+ y, acc))
    }
  }

  def mergeSort[A](seq: HistoricalList[A], preserveSteps: Boolean = false)(implicit o: Ordering[A]): HistoricalList[A] = seq.currentStep match {
    case Nil => seq
    case xs::Nil => seq
    case _ => 
      val (left, right) = seq.currentStep splitAt seq.currentStep.length/2
      merge(mergeSort(HistoricalList[A](left)), mergeSort(HistoricalList(right)), HistoricalList(Nil))
  }

  override def sort[A](list: List[A], preserveSteps: Boolean)(implicit o: Ordering[A]): HistoricalList[A] = { 
    mergeSort(HistoricalList(list), preserveSteps).copy(original = list)
  }
}
