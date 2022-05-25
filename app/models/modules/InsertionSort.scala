package models.modules

import models.behaviors.{ InsertionSorting, RandomOrdering }

case object InsertionSort extends InsertionSorting with RandomOrdering
