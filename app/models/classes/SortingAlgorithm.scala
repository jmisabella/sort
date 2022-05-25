package models.classes

sealed trait SortingAlgorithm

case object Bubble extends SortingAlgorithm
case object Heap extends SortingAlgorithm
case object Insertion extends SortingAlgorithm
case object Merge extends SortingAlgorithm
case object Quick extends SortingAlgorithm
case object Selection extends SortingAlgorithm

object SortingAlgorithm {
  def apply(input: String): SortingAlgorithm = input match {
    case null => throw new IllegalArgumentException("Missing required input to specify sorting algorithm")
    case s if (s.trim() == "") => throw new IllegalArgumentException("Missing required input to specify sorting algorithm")
    case s if (s.trim().toLowerCase().startsWith("bubble")) => Bubble
    case s if (s.trim().toLowerCase().startsWith("heap")) => Heap
    case s if (s.trim().toLowerCase().startsWith("insertion")) => Insertion
    case s if (s.trim().toLowerCase().startsWith("merge")) => Merge
    case s if (s.trim().toLowerCase().startsWith("quick")) => Quick
    case s if (s.trim().toLowerCase().startsWith("selection")) => Selection
    case s => throw new IllegalArgumentException(s"Unexpected SortingAlgorithm input [$s]; allowed values are [bubble, heap, insertion, merge, quick, selection]")
  }
}