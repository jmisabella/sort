package models.behaviors

import models.classes.HistoricalList
import scala.annotation.tailrec

trait MergeSort extends Sort {

  @tailrec
  final def merge[A](seq1: HistoricalList[A], seq2: HistoricalList[A], acc: HistoricalList[A])(implicit o: Ordering[A]): HistoricalList[A] = { 
    (seq1.currentStep, seq2.currentStep) match {
      // case (Nil, _) => HistoricalList2(acc.currentStep ++ seq2.currentStep, acc.previousSteps ++ List(acc.currentStep ++ seq2.currentStep))
      // case (_, Nil) => HistoricalList2(acc.currentStep ++ seq1.currentStep, acc.previousSteps ++ List(acc.currentStep ++ seq1.currentStep))
      case (Nil, _) => HistoricalList(acc.currentStep ++ seq2.currentStep, acc.previousSteps)
      case (_, Nil) => HistoricalList(acc.currentStep ++ seq1.currentStep, acc.previousSteps)
      case (x::xs, y::ys) =>
        // if(o.lt(x, y)) merge(HistoricalList(xs, seq1.previousSteps) ,seq2, HistoricalList(acc.currentStep :+ x, List(acc.currentStep :+ x) ++ acc.previousSteps))
        if(o.lt(x, y)) merge(HistoricalList(xs, seq1.previousSteps) ,seq2, HistoricalList(acc.currentStep :+ x, acc))
        // else merge(seq1,HistoricalList(ys, seq2.previousSteps), HistoricalList(acc.currentStep :+ y, List(acc.currentStep :+ y) ++ acc.previousSteps))
        else merge(seq1,HistoricalList(ys, seq2.previousSteps), HistoricalList(acc.currentStep :+ y, acc))
    }
  }

  def mergeSort[A](seq: HistoricalList[A], preserveSteps: Boolean = false)(implicit o: Ordering[A]): HistoricalList[A] = seq.currentStep match {
    case Nil => seq
    case xs::Nil => seq //HistoricalList2(List(xs), seq.previousSteps) 
    case _ => 
      val (left, right) = seq.currentStep splitAt seq.currentStep.length/2
      merge(mergeSort(HistoricalList[A](left)), mergeSort(HistoricalList(right)), HistoricalList(Nil))
  }

  override def sort[A](list: List[A], preserveSteps: Boolean)(implicit o: Ordering[A]): HistoricalList[A] = { 
    mergeSort(HistoricalList(list), preserveSteps).copy(original = list)
    // val result = mergeSort(HistoricalList2(list), preserveSteps)
    // result.copy(history = result.history.distinct, original = list)
  }
}

// object FunctionsFromScalaAlgorithmsCom {
  
//   def merge(inputs: List[List[Int]]): List[Int] = {
//     @tailrec
//     def rec(inputsRemaining: List[List[Int]], result: List[Int]): List[Int] =
//       inputsRemaining.filter(_.nonEmpty).sortBy(_.headOption) match {
//         case (smallest :: restA) :: restB => rec(restA :: restB, smallest :: result)
//         case _ => result.reverse
//       }
//     rec(inputsRemaining = inputs, result = List.empty)
//   }
  
//   def iterate[T](iterations: Int, initial: T)(f: T => T): T =
//     LazyList.iterate(initial)(f).take(iterations + 1).last

//   def mergeSort(input: Vector[Int]): Vector[Int] = {
//     val MergeWidth = 2
//     iterate(
//       iterations =
//         if (input.isEmpty) 0
//         else
//           Math
//             .ceil(Math.log(input.length.toDouble) / Math.log(MergeWidth.toDouble))
//             .toInt,
//       initial = input.iterator.map(i => i :: Nil)
//     )(level =>
//       level.grouped(MergeWidth).map(inputs => merge(inputs.toList))
//     ).flatten.toVector
//   }
// }