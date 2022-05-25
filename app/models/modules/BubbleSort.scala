package models.modules

import models.behaviors.{ BubbleSorting, RandomOrdering }

case object BubbleSort extends BubbleSorting with RandomOrdering

