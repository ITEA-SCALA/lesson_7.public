package com.itea.task1

/**
 * @see http://aperiodic.net/phil/scala/s-99/
 */
object Application2 extends App {

  /**
   * @see http://aperiodic.net/phil/scala/s-99/p02.scala
   *      P02 (*) Находим пред-последний элемент списка.
   */
  def penultimateBuiltin[A](ls: List[A]): A =
    if (ls.isEmpty) throw new NoSuchElementException
    else ls.init.last
  println( "P02 (penultimateBuiltin) = " +
    penultimateBuiltin(List(1, 1, 2, 3, 5, 8)) )

  def penultimateRecursive[A](ls: List[A]): A = ls match {
    case h :: _ :: Nil => h
    case _ :: tail     => penultimateRecursive(tail)
  }
  println( "P02 (penultimateRecursive) = " +
    penultimateRecursive(List(1, 1, 2, 3, 5, 8)) )

  def lastNthRecursive[A](n: Int, ls: List[A]): A = {
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
    else lastNthR(n, ls, ls)
  }
  println( "P02 (lastNthRecursive) = " +
    lastNthRecursive(1, List(1, 1, 2, 3, 5, 8)) )


  /**
   * @see http://aperiodic.net/phil/scala/s-99/p03.scala
   *      P03 (*) Находим K-й элемент списка.
   */
  def lastNthBuiltin[A](index: Int, list: List[A]): A = {
    if (index <= 0) throw new IllegalArgumentException
    if (list.length < index) throw new NoSuchElementException
    list.takeRight(index).head
  }
  println( "P03 (lastNthBuiltin) = " +
    lastNthBuiltin(1, List(1, 1, 2, 3, 5, 8)) )

  /**
   * @see http://aperiodic.net/phil/scala/s-99/p04.scala
   *      P04 (*) Найдите количество элементов в списке.
   */

  /**
   * @see http://aperiodic.net/phil/scala/s-99/p05.scala
   *      P05 (*) Перевернуть список.
   */
}
