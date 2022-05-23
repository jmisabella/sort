package models.behaviors

import models.classes.{ HistoricalList, Heap, EmptyHeap, NonEmptyHeap }
import scala.annotation.tailrec

trait HeapSort extends Sort {

  def merge[A](a: Heap[A], b: Heap[A], preserveSteps: Boolean = false)(implicit o: Ordering[A]): Heap[A] = {
    val result: Heap[A] = (a, b) match {
      case (x: Heap[A], EmptyHeap(_, _)) => x
      case (EmptyHeap(_, _), x) => x
      case (x: NonEmptyHeap[A], y: NonEmptyHeap[A]) =>
        if (o.gteq(x.element, y.element))
          Heap(x.element, x.left, merge(x.right, y, preserveSteps))
        else
          Heap(y.element, y.left, merge(x, y.right, preserveSteps))
    }
    preserveSteps match {
      case false => result
      case true => Heap(result, result.steps ++ List(toList(result)))
    }
  }

  def toList[A](heap: Heap[A])(implicit o: Ordering[A]): List[A] = toListWithMemory(Nil, heap)

  @tailrec
  final def toListWithMemory[A](acc: List[A], heap: Heap[A], preserveSteps: Boolean = false)(implicit o: Ordering[A]): List[A] = heap match {
    case x: EmptyHeap[A] => acc
    case x: NonEmptyHeap[A] => toListWithMemory(x.element :: acc, merge(x.left, x.right, preserveSteps))
  }
  
  def heapSort[A](xs: Seq[A], preserveSteps: Boolean = false)(implicit o: Ordering[A]): Heap[A] =
    xs.foldLeft(EmptyHeap(): Heap[A])((acc, x) => merge(Heap(x), acc, preserveSteps))

  override def sort[A](list: List[A], preserveSteps: Boolean = false)(implicit o: Ordering[A]): HistoricalList[A] = {
    val resultHeap: Heap[A] = heapSort(list, preserveSteps)
    val steps: List[List[A]] = resultHeap.steps
    val sorted: List[A] = toList(resultHeap)
    HistoricalList(resultHeap.steps, list) 
  }

}