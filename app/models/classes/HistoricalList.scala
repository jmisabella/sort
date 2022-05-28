package models.classes

case class HistoricalList[A](history: List[List[A]], original: Seq[A] = Nil) {
  val currentStep: List[A] = history.reverse.headOption.getOrElse(Nil)
  val previousSteps: List[List[A]] = history.length match {
    case 0 => Nil
    case 1 => Nil
    case _ => history.tail
  }
  override def toString(): String = {
    previousSteps.map(xs => 
      if (xs.length == original.length) {
        // println("BOTH LENGTH: " + xs.length)
        xs.mkString("[", ", ", "]")
      } else {
        // println("XS LENGTH: " + xs.length)
        // println("ORIGINAL LENGTH: " + original.length)
        xs.concat(original.diff(xs)).mkString("[", ", ", "]")
      }
      ).mkString("|")
      // ).mkString("\r\n")
  }
}
object HistoricalList {
  def apply[A](currentState: List[A]): HistoricalList[A] = HistoricalList(Nil ++ List(currentState), currentState.toSeq)

  def apply[A](currentState: List[A], previousSteps: List[List[A]]): HistoricalList[A] = HistoricalList(previousSteps ++ List(currentState))

  def apply[A](currentState: List[A], previousState: HistoricalList[A]): HistoricalList[A] = HistoricalList(previousState.history ++ List(currentState))
}