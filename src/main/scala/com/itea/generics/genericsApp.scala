package com.itea.generics

import scala.{Option => SOption}
import scala.{None => SNone}

sealed trait Option[+T] {

  def isEmpty: Boolean = this match {
    case None => true
    case _    => false
  }

//  def getOrElse(default: T): T = this match {
//    case Some(value) => value
//    case None => default
//  }

  def filter(func: T => Boolean): Option[T] = this match { //TODO: (func: T => Boolean) это объявление анонимной функции
//    case n @ None => n
//    case Some(v) => ???
    case Some(v) if func(v) => this
    case _ => None
  }
}

  case object None extends Option[Nothing]
  case class Some[T](value: T) extends Option[T]

object GenericsApp extends App {

  /*
   *TODO: Анонимная функция - это такой тип который можно передавать в качестве параметров (например, в методе...)
   *TODO:                   - а вычисления для анонимной функции всегда будут ленивые
   *TODO:                   - само передается выражение позже, в месте непосредственного вызова
   *TODO:                   - но вызывать такую анонимную функцию можно уже раньше...
   */
//  val add: (Int, Int) => Int = (a: Int, b: Int) => { a + b }
  /*
   *TODO: вот так объявляется анонимная функция 'add'
   *TODO: левое выражение, перед знаком '=' является объявлением для анонимной функции             add: (Int, Int) => Int
   *TODO: правое выражение, после знака '=' является определением для анонимной функции            (a: Int, b: Int) => { a + b }
   *TODO:                                   где в левой ее части, перед знаком '=>' - значение     (a: Int, b: Int)
   *TODO:                                       а в правой ее части, после знака '=>' - выражение  { a + b }
   */
  val add = (a: Int, b: Int) => a + b //TODO: такую анонимную функцию можно записать короче, через синтаксический сахар (учитывая что Scala сама умеет подставлять типы...)
  println( add(3, 7) ) // 10

  /*
   *TODO: пример использования анонимной функции в другой функции
   *TODO: объявление обычная функции         operation(...): Int
   *TODO: объявление анонимной функции       func1: (Int, Int) => Int
   *TODO: вызов анонимной функции            func1(v1, v2)
   */
//  def operation(v1: Int, v2: Int, func1: (Int, Int) => Boolean): Boolean = { func1(v1, v2) }
  def operation(v1: Int, v2: Int, func1: (Int,Int) => Boolean) = func1(v1, v2) //TODO: такую анонимную функцию можно записать короче, через синтаксический сахар (учитывая что Scala сама умеет подставлять типы...)
  /*
   *TODO: передача выражения для анонимной функции
   */
  println( operation(3, 4, (v1,v2) => v1 == v2) ) // false
  println( operation(3, 3, (v1,v2) => v1 == v2) ) // true


//  def func1(f: Int => Boolean): Unit = println( f(1) ) // true
  def func1(f: Int => Boolean): Unit = println( f(20) )  // false
  val aaa1 = (a: Int) => a < 10
  func1(aaa1)

//  var sumIt: (Int => Int) = (x: Int) => { if(x<=1) 1 else sumIt(x-1)+x }
//  println( sumIt(5) ) // 15


  val tryThis = Some(3)   // Some(3)
//  val tryThis = Some(6) // None
  println( tryThis.filter(i => i < 5) ) //TODO: (i => i < 5) это реализация анонимной функции, где в левой части (перед знаком '=>') - значение, а в правой части (после знака '=>') - выражение

}
