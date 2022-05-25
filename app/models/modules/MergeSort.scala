package models.modules

import models.behaviors.{ MergeSorting, RandomOrdering }

case object MergeSort extends MergeSorting with RandomOrdering
