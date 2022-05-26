package models.modules

import models.behaviors.{ Sorting, BubbleSorting, HeapSorting, InsertionSorting, MergeSorting, RandomOrdering, SelectionSorting, QuickSorting }
import models.classes.{ SortingAlgorithm, HistoricalList }
import models.classes.SortingAlgorithm.{ BUBBLE, HEAP, INSERTION, MERGE, QUICK, SELECTION }

case object Factory {
  private case object bubbleSort extends BubbleSorting with RandomOrdering
  private case object heapSort extends HeapSorting with RandomOrdering
  private case object insertionSort extends InsertionSorting with RandomOrdering
  private case object mergeSort extends MergeSorting with RandomOrdering
  private case object quickSort extends QuickSorting with RandomOrdering
  private case object selectionSort extends SelectionSorting with RandomOrdering

  def run[A](input: String, lst: List[A])(implicit o: Ordering[A]): HistoricalList[A] = SortingAlgorithm(input) match {
    case s if (s == BUBBLE) => bubbleSort.sort(bubbleSort.shuffle(lst)._1)
    case s if (s == HEAP) => heapSort.sort(heapSort.shuffle(lst)._1)
    case s if (s == INSERTION) => insertionSort.sort(insertionSort.shuffle(lst)._1)
    case s if (s == MERGE) => mergeSort.sort(mergeSort.shuffle(lst)._1)
    case s if (s == QUICK) => quickSort.sort(quickSort.shuffle(lst)._1)
    case s if (s == SELECTION) => selectionSort.sort(selectionSort.shuffle(lst)._1)
    case s => throw new IllegalArgumentException(s"Unexpected SortingAlgorithm [$s]") 
  }
    
}