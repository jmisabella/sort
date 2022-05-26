package models.modules

import models.behaviors.{ Sorting, BubbleSorting, HeapSorting, InsertionSorting, MergeSorting, RandomOrdering, SelectionSorting, QuickSorting }
import models.classes.SortingAlgorithm
import models.classes.SortingAlgorithm.{ BUBBLE, HEAP, INSERTION, MERGE, QUICK, SELECTION }

case object Factory {

  private case object BubbleSort extends BubbleSorting with RandomOrdering
  private case object HeapSort extends HeapSorting with RandomOrdering
  private case object InsertionSort extends InsertionSorting with RandomOrdering
  private case object MergeSort extends MergeSorting with RandomOrdering
  private case object QuickSort extends QuickSorting with RandomOrdering
  private case object SelectionSort extends SelectionSorting with RandomOrdering

  def create(input: String): Sorting = SortingAlgorithm(input) match {
    case s if (s == BUBBLE) => BubbleSort
    case s if (s == HEAP) => HeapSort 
    case s if (s == INSERTION) => InsertionSort
    case s if (s == MERGE) => MergeSort
    case s if (s == QUICK) => QuickSort
    case s if (s == SELECTION) => SelectionSort
    case s => throw new IllegalArgumentException(s"Unexpected SortingAlgorithm [$s]") 
  } 
}