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
    val a: ListBuffer[A] = ListBuffer(list: _*)
    val history: ListBuffer[List[A]] = ListBuffer()

    val indexOrdering = Ordering by a.apply
  
    def numberOfLeaves(heapSize: Int): Int = (heapSize + 1) / 2
  
    def children(i: Int, heapSize: Int): Seq[Int] = {
      val leftChild = i * 2 + 1
      leftChild to leftChild + 1 takeWhile (_ < heapSize)
    }
  
    def swap(i: Int, j: Int): Unit = {
      val tmp = a(i)
      a(i) = a(j)
      a(j) = tmp
    }

    // Maintain partial ordering by bubbling down elements
    @tailrec 
    def siftDown(i: Int, heapSize: Int): Unit = {
      val childrenOfI = children(i, heapSize)
      if (childrenOfI nonEmpty) {
        val biggestChild = childrenOfI max indexOrdering
        if (a(i) < a(biggestChild)) {
          swap(i, biggestChild)
          siftDown(biggestChild, heapSize)
        }
      }
    }
  
    // Prepare heap by sifting down all non-leaf elements
    for (i <- a.indices.reverse drop numberOfLeaves(a.size)) siftDown(i, a.size)
  
    // Sort from the end of the array forward, by swapping the highest element,
    // which is always the top of the heap, to the end of the unsorted array
    for (i <- a.indices.reverse) {
      swap(0, i)
      siftDown(0, i)
      history.addOne(a.toList)
    }

    HistoricalList(history.toList, list)
  }

}