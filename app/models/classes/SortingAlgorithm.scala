package models.classes

sealed trait SortingAlgorithm

object SortingAlgorithm {
  case object BUBBLE extends SortingAlgorithm
  case object HEAP extends SortingAlgorithm
  case object INSERTION extends SortingAlgorithm
  case object MERGE extends SortingAlgorithm
  case object QUICK extends SortingAlgorithm
  case object SELECTION extends SortingAlgorithm
  def apply(input: String): SortingAlgorithm = input match {
    case null => throw new IllegalArgumentException("Missing required input to specify sorting algorithm")
    case s if (s.trim() == "") => throw new IllegalArgumentException("Missing required input to specify sorting algorithm")
    case s if (s.trim().toLowerCase().startsWith("bubble")) => BUBBLE
    case s if (s.trim().toLowerCase().startsWith("heap")) => HEAP
    case s if (s.trim().toLowerCase().startsWith("insertion")) => INSERTION
    case s if (s.trim().toLowerCase().startsWith("merge")) => MERGE
    case s if (s.trim().toLowerCase().startsWith("quick")) => QUICK
    case s if (s.trim().toLowerCase().startsWith("selection")) => SELECTION
    case s => throw new IllegalArgumentException(s"Unexpected SortingAlgorithm input [$s]; allowed values are [bubble, heap, insertion, merge, quick, selection]")
  }
}