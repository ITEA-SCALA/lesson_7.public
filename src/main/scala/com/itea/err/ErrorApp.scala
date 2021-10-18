package com.itea.err

import com.itea.generics.Some
import scala.util.{Failure, Success, Try}

object ErrorApp extends App {

  /*
   * Java -> Exception
   * Throwable
   *   Error -> AutoOfMemoryException...
   *   Exception
   *     RuntimeException -> TODO  code error  (в других языках, в частности и в Scala отсутствует обработка подобного рода Exception-ов...)
   *     Checked exception - handle it!
   */

  val i = 2

  // но так никто не пишет
//  try {
//    i / 0
//  } catch {
//    case IllegalArgumentException => println( "IAE" ) // class java.lang.IllegalArgumentException is not a value
//    case ArrayIndexOutOfBoundsException => println( "AIOOBE" ) // class java.lang.ArrayIndexOutOfBoundsException is not a value
//  } finally {
//  }

  // лучше писать либо вот так
  def divideBy2(i: Int) = {
    if (i == 0) None
    else Some(2 / i)
  }

  // в крайнем случае вот так
  Try(i / 0) match {
    case Success(value) => println("OK")
    case Failure(exception) => println("Not OK")
  }
  // либо через Either


  // это плохой пример кода:
  class MyException extends Exception

  def divideBy(i: Int): Double = {
    if (i == 0) throw new MyException  // Nothing
    else 2 / i
  }

  /**
   * Validated:
   * 1. библиотека от Cats-Validated  ...  https://typelevel.org/cats/datatypes/validated.html
   * 2. библиотека от Wix-Accord  .......  https://wix.github.io/accord
   */

  case class User(age: Int, name: String)
  // age >= 18
  // name notEmpty

  val validate: User => Either[List[String], User] = user => ???


  type Validated[T] = Either[List[String], T]
  val validate2: User => Validated[User] = user => ???


  sealed trait Validated2[T] {
    def isOK: Boolean = ???
  }
  case class Ok[T](t: T) extends Validated2[T]
  case class Ko[T](inv: List[String]) extends Validated2[T]

}
