package com.itea.s99.p01

/**
 * Находим последний элемент списка
 * @see http://aperiodic.net/phil/scala/s-99/p01.scala
 */
object LastElementList extends App {

  /*
   * позволяет обрабатывать списки любого типа
   * используем встроенные команды
   */
  def lastBuiltin[A](list: List[A]): A = list.last

  println(
    "lastBuiltin = " + lastBuiltin(List(1, 1, 2, 3, 5, 8)))

  /*
   * паттерн 'match' + рекурсия по списку
   */
  def lastRecursive[A](list: List[A]): A = list match {
    case h :: Nil  => h //TODO:
    case _ :: tail => lastRecursive(tail)
  }

  println(
    "lastRecursive = " + lastRecursive(List(1, 1, 2, 3, 5, 8)))

}
