package models.modules

import models.behaviors.{ HeapSorting, RandomOrdering }

case object HeapSort extends HeapSorting with RandomOrdering
