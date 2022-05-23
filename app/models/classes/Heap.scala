package models.classes

sealed trait Heap[+A] { 
  def rank: Int
  def steps: List[List[A]]
}
case class EmptyHeap[A] (
  rank: Int = 0,
  steps: List[List[A]] = Nil) extends Heap[A]

object EmptyHeap {
  def apply[A](): EmptyHeap[A] = EmptyHeap(0, Nil)
}

case class NonEmptyHeap[A] (
  rank: Int, 
  element: A, 
  left: Heap[A], 
  right: Heap[A], 
  steps: List[List[A]] = Nil) extends Heap[A]

object Heap {
  def apply[A](x: A): Heap[A] =
    this(x, EmptyHeap(), EmptyHeap())

  def apply[A](x: A, a: Heap[A], b: Heap[A]): Heap[A] = (a, b) match {
    case (aa, bb) if (aa.rank > bb.rank) => NonEmptyHeap(b.rank + 1, x, a, b, a.steps ++ b.steps)
    case (aa, bb) if (aa.rank <= bb.rank) => NonEmptyHeap(a.rank + 1, x, b, a, a.steps ++ b.steps)
    case (_, _) => NonEmptyHeap(a.rank + 1, x, b, a, a.steps ++ b.steps)
  }

  def apply[A](heap: Heap[A], steps: List[List[A]]): Heap[A] = heap match {
    case h: EmptyHeap[A] => h.copy(steps = steps)
    case h: NonEmptyHeap[A] => h.copy(steps = steps)
  }
}