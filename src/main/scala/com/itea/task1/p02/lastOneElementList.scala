package com.itea.task1.p02

/**
 * Найдите предпоследний элемент списка.
 * @see http://aperiodic.net/phil/scala/s-99/p02.scala
 */
object LastOneElementList extends App {

  /*
   * со встроенными командами
   */
  def penultimateBuiltin[A](list: List[A]): A =
    if (list.isEmpty) throw new NoSuchElementException
    else list.init.last

  println(
    "penultimateBuiltin = " + penultimateBuiltin(List(1, 1, 2, 3, 5, 8)))

  /*
   * паттерн 'match' + рекурсия по списку
   */
  def penultimateRecursive[A](list: List[A]): A = list match {
    case h :: _ :: Nil => h //TODO:
    case _ :: tail     => penultimateRecursive(tail)
  }

  println(
    "penultimateRecursive = " + penultimateRecursive(List(1, 1, 2, 3, 5, 8)))

    /*
     *
     */
  def lastNthBuiltin[A](index: Int, list: List[A]): A = {
    if (index <= 0) throw new IllegalArgumentException
    if (list.length < index) throw new NoSuchElementException
    list.takeRight(index).head
  }

  println(
    "lastNthBuiltin = " + lastNthBuiltin(2, List(1, 1, 2, 3, 5, 8)))

  /*
   * через внутреннюю функцию 'lastNthR' + паттерн 'match' + рекурсия
   */
  def lastNthRecursive[A](n: Int, list: List[A]): A = {
    def lastNthR(count: Int, resultList: List[A], curList: List[A]): A =
      curList match {
        case Nil if count > 0 => throw new NoSuchElementException
        case Nil              => resultList.head
        case _ :: tail        =>
          lastNthR(count - 1,
            if (count > 0) resultList else resultList.tail,
            tail)
      }
    if (n <= 0) throw new IllegalArgumentException
    else lastNthR(n, list, list)
  }

  println(
    "lastNthRecursive = " + lastNthRecursive(2, List(1, 1, 2, 3, 5, 8)))

}
