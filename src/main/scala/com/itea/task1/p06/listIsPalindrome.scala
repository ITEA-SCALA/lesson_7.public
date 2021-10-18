package com.itea.task1.p06

/**
 * Узнайте, является ли список палиндромом.
 * @see http://aperiodic.net/phil/scala/s-99/p06.scala
 */
object ListIsPalindrome extends App {
  /*
   * проходит по списку дважды: один раз чтобы перевернуть его, и один раз чтобы проверить равенство
   */
  def isPalindrome[A](list: List[A]): Boolean = list == list.reverse

  println(
    "isPalindrome (true) = " + isPalindrome(List(1, 2, 3, 2, 1)))
  println(
    "isPalindrome (false) = " + isPalindrome(List(1, 2, 3, 4, 5)))

}
