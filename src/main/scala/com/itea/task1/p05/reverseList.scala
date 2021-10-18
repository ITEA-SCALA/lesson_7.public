package com.itea.task1.p05

/**
 * Перевернуть список.
 * @see http://aperiodic.net/phil/scala/s-99/p05.scala
 */
object ReverseList extends App {
  /*
   * со встроенными командами
   */
  def reverseBuiltin[A](list: List[A]): List[A] = list.reverse

  println(
    "reverseBuiltin = " + reverseBuiltin(List(1, 1, 2, 3, 5, 8)))

  /*
   * паттерн 'match' + рекурсия по списку
   */
  def reverseRecursive[A](list: List[A]): List[A] = list match {
    case Nil       => Nil
    case h :: tail => reverseRecursive(tail) ::: List(h)
  }

  println(
    "reverseRecursive = " + reverseRecursive(List(1, 1, 2, 3, 5, 8)))

  /*
   * через внутреннюю функцию 'reverseR' + паттерн 'match' + рекурсия
   */
  def reverseTailRecursive[A](ls: List[A]): List[A] = {
    def reverseR(result: List[A], curList: List[A]): List[A] = curList match {
      case Nil       => result
      case h :: tail => reverseR(h :: result, tail)
    }
    reverseR(Nil, ls)
  }

  println(
    "reverseTailRecursive = " + reverseTailRecursive(List(1, 1, 2, 3, 5, 8)))

  /*
   * функциональное решение, через foldLeft
   */
  def reverseFunctional[A](list: List[A]): List[A] = list.foldLeft(List[A]()) { (r, h) => h :: r }

  println(
    "reverseFunctional = " + reverseFunctional(List(1, 1, 2, 3, 5, 8)))

}
