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
    case _ => input.toLowerCase().replace("sort", "").trim() match {
      case "" => throw new IllegalArgumentException("Missing required input to specify sorting algorithm") 
      case "bubble" => BUBBLE
      case "heap" => HEAP
      case "insertion" => INSERTION
      case "merge" => MERGE
      case "quick" => QUICK
      case "selection" => SELECTION
      case s => throw new IllegalArgumentException(s"Unexpected SortingAlgorithm input [$s]; allowed values are [bubble, heap, insertion, merge, quick, selection]")
    } 
  }
}