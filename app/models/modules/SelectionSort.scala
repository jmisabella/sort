package models.modules

import models.behaviors.{ SelectionSorting, RandomOrdering }

case object SelectionSort extends SelectionSorting with RandomOrdering
