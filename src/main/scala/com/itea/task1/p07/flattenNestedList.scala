package com.itea.task1.p07

/**
 * Сглаживание структуры вложенного списка.
 * @see http://aperiodic.net/phil/scala/s-99/p07.scala
 */
object FlattenNestedList extends App {
  /*
   *
   */
  def flatten(ls: List[Any]): List[Any] = ls flatMap {
    case ms: List[_] => flatten(ms)
    case e => List(e)
  }

  println(
    "flatten = " + flatten(List(List(1, 1), 2, List(3, List(5, 8)))))
  
}
