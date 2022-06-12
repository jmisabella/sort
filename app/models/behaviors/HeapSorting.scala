package models.behaviors

import models.classes.HistoricalList
import scala.collection.mutable.ListBuffer
import scala.annotation.tailrec
import scala.language.postfixOps

trait HeapSorting extends Sorting {

  override def sort[A](list: List[A])(implicit o: Ordering[A]): HistoricalList[A] = isSorted(list) match {
    case true => HistoricalList(List(list)) // only attempt to sort if list is not already sorted
    case false => heapSort(list)
  }

  def heapSort[A](list: List[A])(implicit ord: Ordering[A]): HistoricalList[A] = {
    import ord._
    val lst: ListBuffer[A] = ListBuffer(list: _*)
    val history: ListBuffer[List[A]] = ListBuffer()

    val indexOrdering = Ordering by lst.apply
  
    def numberOfLeaves(heapSize: Int): Int = (heapSize + 1) / 2
  
    def children(i: Int, heapSize: Int): Seq[Int] = {
      val leftChild = i * 2 + 1
      leftChild to leftChild + 1 takeWhile (_ < heapSize)
    }
  
    def swap(i: Int, j: Int): Unit = {
      val tmp = lst(i)
      lst(i) = lst(j)
      lst(j) = tmp
    }

    // Maintain partial ordering by bubbling down elements
    @tailrec 
    def siftDown(i: Int, heapSize: Int): Unit = {
      val childrenOfI = children(i, heapSize)
      if (childrenOfI nonEmpty) {
        val biggestChild = childrenOfI max indexOrdering
        if (lst(i) < lst(biggestChild)) {
          // history.addOne(lst.toList)
          swap(i, biggestChild)
          siftDown(biggestChild, heapSize)
        }
      } else {
        history.addOne(lst.toList)
      }
    }

    // Prepare heap by sifting down all non-leaf elements
    for (i <- lst.indices.reverse drop numberOfLeaves(lst.size)) siftDown(i, lst.size)
  
    // Sort from the end of the array forward, by swapping the highest element,
    // which is always the top of the heap, to the end of the unsorted array
    for (i <- lst.indices.reverse) {
      swap(0, i)
      siftDown(0, i)
      history.addOne(lst.toList)
    }
    HistoricalList(history.toList.distinct, list)
  }
}