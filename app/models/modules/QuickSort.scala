package models.modules

import models.behaviors.{ QuickSorting, RandomOrdering }

case object QuickSort extends QuickSorting with RandomOrdering
