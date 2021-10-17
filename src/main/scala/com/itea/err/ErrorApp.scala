package com.itea.err

import com.itea.generics.Some
import scala.util.{Failure, Success, Try}

object ErrorApp extends App {

  /*
   * Java -> Exception
   * Throwable
   *   Error -> AutoOfMemoryException...
   *   Exception
   *     RuntimeException -> code error
   *     Checked exception - handle it!
   */

  val i = 2

  // но так никто не пишет
  try {
    i / 0
  } catch {
    case IllegalArgumentException => println( "IAE" )
    case ArrayIndexOutOfBoundsException => println( "AIOOBE" )
  } finally {
  }

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


}
