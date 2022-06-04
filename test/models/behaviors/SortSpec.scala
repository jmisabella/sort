package models.behaviors

import models.classes.{ SortStepResult, HistoricalList }
import org.scalatest.flatspec.AnyFlatSpec

class SortSpec extends AnyFlatSpec {

  case object selectionSort extends SelectionSorting
  case object bubbleSort extends BubbleSorting
  case object insertionSort extends InsertionSorting
  case object heapSort extends HeapSorting
  case object mergeSort extends MergeSorting
  case object quickSort extends QuickSorting

  "SelectionSort" should "consider empty list already sorted" in {
    val lst: List[Int] = Nil
    val results: HistoricalList[Int] = selectionSort.sort(lst)
    val (sorted, passes): (List[Int], Int) = (results.currentStep, results.history.length)
    assert(passes == 0, s"Expected [0], actual [$passes]")

    println("SELECTION SORT") 
    println(results)
    println("ORIGINAL: " + results.original.mkString(", "))
  }

  it should "consider single element list already sorted" in {
    val lst: List[Int] = List(55) 
    val results: HistoricalList[Int] = selectionSort.sort(lst)
    val (sorted, passes): (List[Int], Int) = (results.currentStep, results.history.length)
    assert(passes == 0, s"Expected [0], actual [$passes]")
    println("SELECTION SORT") 
    println(results)
    println("ORIGINAL: " + results.original.mkString(", "))
  }

  it should "consider a sorted list already sorted" in {
    val lst: List[Int] = List(2, 4, 6) 
    val results: HistoricalList[Int] = selectionSort.sort(lst)
    val (sorted, passes): (List[Int], Int) = (results.currentStep, results.history.length)
    assert(passes == 0, s"Expected [0], actual [$passes]")
    println("SELECTION SORT") 
    println(results)
    println("ORIGINAL: " + results.original.mkString(", "))
  }

  it should "sort [2, 1, 3] in 1 pass" in {
    val lst: List[Int] = List(2, 1, 3) 
    val results: HistoricalList[Int] = selectionSort.sort(lst)
    val (sorted, passes): (List[Int], Int) = (results.currentStep, results.history.length)
    assert(passes == 1, s"Expected [1], actual [$passes]")
    println("SELECTION SORT") 
    println(results)
    println("ORIGINAL: " + results.original.mkString(", "))
  }

  it should "sort [9, 8, 6, 4, 3, 1] in 3 passes" in {
    val lst: List[Int] = List(9, 8, 6, 4, 3, 1)
    val results: HistoricalList[Int] = selectionSort.sort(lst)
    val (sorted, passes): (List[Int], Int) = (results.currentStep, results.history.length)
    assert(passes == 3, s"Expected [3], actual [$passes]")
    println("SELECTION SORT") 
    println(results)
    println("ORIGINAL: " + results.original.mkString(", "))
  }

  // it should "sort [2, 1, 3, 5, 4, 7, 9, 8] in 1 pass" in {
  //   val lst: List[Int] = List(2, 1, 3, 5, 4, 7, 9, 8) 
  //   val (sorted, passes): (List[Int], Int) = selectionSort.sort(lst)
  //   assert(passes == 1, s"Expected [1], actual [$passes]")
  // }

  // it should "sort [64, 25, 12, 22, 11] in 1 pass" in {
  //   val lst: List[Int] = List(64, 25, 12, 22, 11)
  //   val isSortedBefore: Boolean = selectionSort.isSorted(lst)
  //   val next: SortResultState[Int] = selectionSort.sortStep(lst)
  //   val isSortedAfter: Boolean = selectionSort.isSorted(next.list)
  //   assert(!isSortedBefore, s"Expected [false], actual [$isSortedBefore]") 
  //   assert(isSortedAfter, s"Expected [true], actual [$isSortedAfter]") 
   
  //   val (sorted, passes): (List[Int], Int) = selectionSort.sort(lst)
  //   assert(passes == 1, s"Expected [1], actual [$passes]")
  // }

  "BubbleSort" should "consider empty list already sorted" in {
    val lst: List[Int] = Nil
    val results: HistoricalList[Int] = bubbleSort.sort(lst)
    val (sorted, passes): (List[Int], Int) = (results.currentStep, results.history.length)
    assert(passes == 0, s"Expected [0], actual [$passes]")
    println("BUBBLE SORT") 
    println(results)
    println("ORIGINAL: " + results.original.mkString(", "))
  }

  it should "consider single element list already sorted" in {
    val lst: List[Int] = List(55) 
    val results: HistoricalList[Int] = bubbleSort.sort(lst)
    val (sorted, passes): (List[Int], Int) = (results.currentStep, results.history.length)
    assert(passes == 0, s"Expected [0], actual [$passes]")
    println("BUBBLE SORT") 
    println(results)
    println("ORIGINAL: " + results.original.mkString(", "))
  }

  it should "consider a sorted list already sorted" in {
    val lst: List[Int] = List(2, 4, 6) 
    val results: HistoricalList[Int] = bubbleSort.sort(lst)
    val (sorted, passes): (List[Int], Int) = (results.currentStep, results.history.length)
    assert(passes == 0, s"Expected [0], actual [$passes]")
    println("BUBBLE SORT") 
    println(results)
    println("ORIGINAL: " + results.original.mkString(", "))
  }

  it should "sort [2, 1, 3] in 1 pass" in {
    val lst: List[Int] = List(2, 1, 3) 
    val results: HistoricalList[Int] = bubbleSort.sort(lst)
    val (sorted, passes): (List[Int], Int) = (results.currentStep, results.history.length)
    assert(passes == 1, s"Expected [1], actual [$passes]")
    println("BUBBLE SORT") 
    println(results)
    println("ORIGINAL: " + results.original.mkString(", "))
  }

  it should "sort [2, 1, 3, 5, 4, 7, 9, 8] in 1 pass" in {
    val lst: List[Int] = List(2, 1, 3, 5, 4, 7, 9, 8) 
    val results: HistoricalList[Int] = bubbleSort.sort(lst)
    val (sorted, passes): (List[Int], Int) = (results.currentStep, results.history.length)
    assert(passes == 1, s"Expected [1], actual [$passes]")
    println("BUBBLE SORT") 
    println(results)
    println("ORIGINAL: " + results.original.mkString(", "))
  }

  it should "sort [5, 1, 4, 2, 8] in 2 passes" in {
    val lst: List[Int] = List(5, 1, 4, 2, 8)
    val next: SortStepResult[Int] = bubbleSort.sortStep(lst)
    println("NEXT: " + next.list.mkString(", ")) 
    val results: HistoricalList[Int] = bubbleSort.sort(lst)
    val (sorted, passes): (List[Int], Int) = (results.currentStep, results.history.length)
    assert(passes == 2, s"Expected [2], actual [$passes]")
    assert(bubbleSort.isSorted(sorted))
    println("BUBBLE SORT") 
    println(results)
    println("ORIGINAL: " + results.original.mkString(", "))
  }
  
  it should "sort [9, 8, 6, 4, 3, 1] in 5 passes" in {
    val lst: List[Int] = List(9, 8, 6, 4, 3, 1)
    val results: HistoricalList[Int] = bubbleSort.sort(lst)
    val (sorted, passes): (List[Int], Int) = (results.currentStep, results.history.length)
    assert(passes == 5, s"Expected [5], actual [$passes]")
    println("BUBBLE SORT") 
    println(results)
    println("ORIGINAL: " + results.original.mkString(", "))
  }
  
  it should "sort [64, 25, 12, 22, 11] in 4 passes" in {
    val lst: List[Int] = List(64, 25, 12, 22, 11)
    val results: HistoricalList[Int] = bubbleSort.sort(lst)
    val (sorted, passes): (List[Int], Int) = (results.currentStep, results.history.length)
    assert(passes == 4, s"Expected [4], actual [$passes]")
    println("BUBBLE SORT") 
    println(results)
    println("ORIGINAL: " + results.original.mkString(", "))
  }

  "InsertionSort" should "consider empty list already sorted" in {
    val lst: List[Int] = Nil
    val results: HistoricalList[Int] = insertionSort.sort(lst)
    val (sorted, passes): (List[Int], Int) = (results.currentStep, results.history.length)
    assert(passes == 0, s"Expected [0], actual [$passes]")
    println("INSERTION SORT") 
    println(results)
    println("ORIGINAL: " + results.original.mkString(", "))
  }

  it should "consider single element list already sorted" in {
    val lst: List[Int] = List(55) 
    val results: HistoricalList[Int] = insertionSort.sort(lst)
    val (sorted, passes): (List[Int], Int) = (results.currentStep, results.history.length)
    assert(passes == 0, s"Expected [0], actual [$passes]")
    println("INSERTION SORT") 
    println(results)
    println("ORIGINAL: " + results.original.mkString(", "))
  }

  it should "consider a sorted list already sorted" in {
    val lst: List[Int] = List(2, 4, 6) 
    val results: HistoricalList[Int] = insertionSort.sort(lst)
    val (sorted, passes): (List[Int], Int) = (results.currentStep, results.history.length)
    assert(passes == 0, s"Expected [0], actual [$passes]")
    println("INSERTION SORT") 
    println(results)
    println("ORIGINAL: " + results.original.mkString(", "))
  }

  it should "sort [2, 1, 3] in 2 passes" in {
    val lst: List[Int] = List(2, 1, 3) 
    val results: HistoricalList[Int] = insertionSort.sort(lst)
    val (sorted, passes, steps): (List[Int], Int, List[List[Int]]) = (results.currentStep, results.history.length, results.previousSteps)
    assert(passes == 2, s"Expected [2], actual [$passes]")
    println("INSERTION SORT") 
    println(results)
    println("ORIGINAL: " + results.original.mkString(", "))
  }

  it should "[12, 11, 13, 5, 6] in 5 passes" in {
    val lst: List[Int] = List(12, 11, 13, 5, 6)
    val results: HistoricalList[Int] = insertionSort.sort(lst)
    val (sorted, passes, steps): (List[Int], Int, List[List[Int]]) = (results.currentStep, results.history.length, results.previousSteps)
    assert(passes == 5, s"Expected [5], actual [$passes]")
    println("INSERTION SORT") 
    println(results)
    println("ORIGINAL: " + results.original.mkString(", "))
  }
  
  "HeapSort" should "consider empty list already sorted" in {
    val lst: List[Int] = Nil
    val results: HistoricalList[Int] = heapSort.sort(lst)
    val (sorted, passes): (List[Int], Int) = (results.currentStep, results.history.length)
    assert(passes == 1, s"Expected [1], actual [$passes]")
    println("HEAP SORT") 
    println(results)
    println("SORTED: " + results.currentStep.mkString(", "))
    println("ORIGINAL: " + results.original.mkString(", "))
  }

  it should "consider single element list already sorted" in {
    val lst: List[Int] = List(55) 
    val results: HistoricalList[Int] = heapSort.sort(lst)//(Heap.ordering[Int])
    val (sorted, passes): (List[Int], Int) = (results.currentStep, results.history.length)
    assert(passes == 1, s"Expected [1], actual [$passes]")
    println("HEAP SORT") 
    println(results)
    println("SORTED: " + results.currentStep.mkString(", "))
    println("ORIGINAL: " + results.original.mkString(", "))
  }

  it should "consider a sorted list already sorted" in {
    val lst: List[Int] = List(2, 4, 6) 
    val results: HistoricalList[Int] = heapSort.sort(lst)//(Heap.ordering[Int])
    val (sorted, passes): (List[Int], Int) = (results.currentStep, results.history.length)
    assert(heapSort.isSorted(results.currentStep))
    assert(passes == 1, s"Expected [1], actual [$passes]")
    println("HEAP SORT") 
    println(results)
    println("SORTED: " + results.currentStep.mkString(", "))
    println("ORIGINAL: " + results.original.mkString(", "))
  }

  it should "sort [2, 1, 3] in 3 passes" in {
    val lst: List[Int] = List(2, 1, 3) 
    val results: HistoricalList[Int] = heapSort.sort(lst)//(Heap.ordering[Int])
    assert(heapSort.isSorted(results.currentStep))
    assert(results.history.length == 3, s"Expected [3], actual [${results.history.length}]")
    println("HEAP SORT") 
    println(results)
    println("SORTED: " + results.currentStep.mkString(", "))
    println("ORIGINAL: " + results.original.mkString(", "))
  }

  it should "sort [12, 11, 13, 5, 6]" in {
    val lst: List[Int] = List(12, 11, 13, 5, 6)
    val results: HistoricalList[Int] = heapSort.sort(lst)
    println("RESULT: " + results.currentStep)
    assert(heapSort.isSorted(results.currentStep))
    assert(results.history.length == 5, s"Expected [5], actual [${results.history.length}]")
    println("HEAP SORT") 
    println(results)
    println("SORTED: " + results.currentStep.mkString(", "))
    println("ORIGINAL: " + results.original.mkString(", "))
  }

  it should "sort [4, 10, 3, 5, 1] in 5 passes" in {
    val lst: List[Int] = List(4, 10, 3, 5, 1)
    val results: HistoricalList[Int] = heapSort.sort(lst)
    println("RESULT: " + results.currentStep)
    assert(heapSort.isSorted(results.currentStep))
    assert(results.history.length == 5, s"Expected [5], actual [${results.history.length}]")
    println("HEAP SORT") 
    println(results)
    println("SORTED: " + results.currentStep.mkString(", "))
    println("ORIGINAL: " + results.original.mkString(", "))
  }

  "MergeSort" should "consider empty list already sorted" in {
    val lst: List[Int] = Nil
    val results: HistoricalList[Int] = mergeSort.sort(lst)
    val (sorted, passes): (List[Int], Int) = (results.currentStep, results.history.length)
    assert(passes == 1, s"Expected [1], actual [$passes]")
    println("MERGE SORT") 
    println(results)
    println("ORIGINAL: " + results.original.mkString(", "))
  }

  it should "consider single element list already sorted" in {
    val lst: List[Int] = List(55) 
    val results: HistoricalList[Int] = mergeSort.sort(lst)
    val (sorted, passes): (List[Int], Int) = (results.currentStep, results.history.length)
    assert(passes == 1, s"Expected [1], actual [$passes]")
    println("MERGE SORT") 
    println(results)
    println("ORIGINAL: " + results.original.mkString(", "))
  }

  it should "consider a sorted list already sorted" in {
    val lst: List[Int] = List(2, 4, 6) 
    val results: HistoricalList[Int] = mergeSort.sort(lst)
    val (sorted, passes): (List[Int], Int) = (results.currentStep, results.history.length)
    assert(mergeSort.isSorted(results.currentStep))
    // assert(passes == 1, s"Expected [1], actual [$passes]")
    println("MERGE SORT") 
    println(results)
    println("ORIGINAL: " + results.original.mkString(", "))
  }

  it should "sort [12, 11, 13, 5, 6] in 5 passes" in {
    val lst: List[Int] = List(12, 11, 13, 5, 6)
    val results: HistoricalList[Int] = mergeSort.sort(lst)
    val passes: Int = results.history.length
    assert(mergeSort.isSorted(results.currentStep))
    assert(passes == 5, s"Expected [5], actual [$passes]")
    println("MERGE SORT") 
    println(results)
    println("ORIGINAL: " + results.original.mkString(", "))
  }

  it should "sort [44, 12, 99, 11, 13, 12, 7, 5, 6] in 8 passes" in {
    val lst: List[Int] = List(44, 12, 99, 11, 13, 12, 7, 5, 6)
    val results: HistoricalList[Int] = mergeSort.sort(lst)
    val passes: Int = results.history.length
    assert(mergeSort.isSorted(results.currentStep))
    assert(passes == 8, s"Expected [8], actual [$passes]")
    println("MERGE SORT") 
    println(results)
    println("ORIGINAL: " + results.original.mkString(", "))
  }

  "QuickSort" should "consider empty list already sorted" in {
    val lst: List[Int] = Nil
    val results: HistoricalList[Int] = quickSort.sort(lst)
    val (sorted, passes): (List[Int], Int) = (results.currentStep, results.history.length)
    // assert(passes == 1, s"Expected [1], actual [$passes]")
    println("QUICK SORT") 
    println(results)
    println("ORIGINAL: " + results.original.mkString(", "))
  }

  it should "consider single element list already sorted" in {
    val lst: List[Int] = List(55) 
    val results: HistoricalList[Int] = quickSort.sort(lst)
    val (sorted, passes): (List[Int], Int) = (results.currentStep, results.history.length)
    // assert(passes == 1, s"Expected [1], actual [$passes]")
    println("QUICK SORT") 
    println(results)
    println("ORIGINAL: " + results.original.mkString(", "))
  }

  it should "consider a sorted list already sorted" in {
    val lst: List[Int] = List(2, 4, 6) 
    val results: HistoricalList[Int] = quickSort.sort(lst)
    val (sorted, passes): (List[Int], Int) = (results.currentStep, results.history.length)
    assert(quickSort.isSorted(results.currentStep))
    // assert(passes == 1, s"Expected [1], actual [$passes]")
    println("QUICK SORT") 
    println(results)
    println("ORIGINAL: " + results.original.mkString(", "))
  }

  it should "sort [12, 11, 13, 5, 6] " in {
    val lst: List[Int] = List(12, 11, 13, 5, 6)
    val results: HistoricalList[Int] = quickSort.sort(lst)
    val passes: Int = results.history.length
    assert(quickSort.isSorted(results.currentStep))
    // assert(passes == 5, s"Expected [5], actual [$passes]")
    println("QUICK SORT") 
    println(results)
    println("ORIGINAL: " + results.original.mkString(", "))
  }

  it should "sort [44, 12, 99, 11, 13, 12, 7, 5, 6] " in {
    val lst: List[Int] = List(44, 12, 99, 11, 13, 12, 7, 5, 6)
    val results: HistoricalList[Int] = quickSort.sort(lst)
    val passes: Int = results.history.length
    assert(quickSort.isSorted(results.currentStep))
    // assert(passes == 8, s"Expected [8], actual [$passes]")
    println("QUICK SORT") 
    println(results)
    println("ORIGINAL: " + results.original.mkString(", "))
  }
}
