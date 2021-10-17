package com.itea.functions

import java.time.LocalDate
import scala.concurrent.ExecutionContext
import com.itea.utlis.math.min

object FunctionApp extends App {

  val inc: Int => Int  = { x =>
    x + 1
  }
  val stringify: Int => String  = { x =>
    if (2 < x) "Many"
    else "Several"
  }
  println( "inc '2' = " + inc(2) ) // inc '2' = 3

  /**
   * Функцию можно композировать:
   * TODO:  после того как ты взял функцию 'inc', примени функцию 'stringify'
   * TODO:  и возвращать 'andThen' тоже будет функцию
   * (и если так подумать то очень многое можно представить себе как функцию)
   * здесь мы не сделали никакого вычисления, никакой полезной работы здесь не произошло мы просто определили список того что мы будем делать...
   */
  val intToString: Int => String  =  inc.andThen(stringify) // TODO:   stringify( inc(x) )   а так это выглядело-бы в процедурном стиле
  println( "intToString = " + intToString )                 // intToString = scala.Function1$$Lambda$6/9266866@b5cdba
  println( "intToString '1' = " + intToString(1) )          // intToString '1' = Several
  println( "intToString '2' = " + intToString(2) )          // intToString '2' = Many

  /**
   * Тут вопрос, насколько серьезно нужно относиться к математическому определению функции, может быть не нужно следовать жестко математике:
   * - 'AkkaHttp' Lightbend-стэк и Martin Odersky считают что не нужно так ревностно относиться...
   * - 'http4s', 'Cats' (есть часть комьюнити) больше всех говорят, что так нельзя, если у есть функция значит возвращай Either либо ошибку либо респонс
   *
   * TODO: так работает 'AkkaHttp' фреймворк   ...   HttpRequest => HttpResponse | 500
   *       так работает 'http4s' фреймворк   .....   HttpRequest => Either[Error, HttpResponse]
   */

  val inc2: Int => Int = _ + 1 // TODO: то же самое что и  'val inc2: Int => Int = x => x + 1'  ТАКОЙ `АНДЕРСКОР` МОЖНО ПРИМЕНЯТЬ КОГДА 'X' ИСПОЛЬЗУЕТСЯ ТОЛЬКО ОДИН РАЗ
  val inc3: Int => Int = { _ + 1 }
  val inc4: Int => Int = ( _ + 1 )
  val inc5: Int => Int = _.*(3)

  val l1 = List(1, 2, 3).filter(i => i > 2)
  val l2 = List(1, 2, 3).filter(_ > 2)

  val _ = 1 // ..?

  val complexFun: Int => Int = { arg => // TODO: это абсолютно тоже самое что и  'val complexFun: Int => Int = arg => {'  НО СМЫСЛ В ТОМ ЧТО У НАС ЕСТЬ ОДИН БЛОК
    val y = arg / 3
    val o = y * arg
    /* return */ o + y
  }

  val complexFun2: (Int, Int) => Int = { (arg1, arg2) => // здесь разобрали тапл (Int, Int) // one more* Currying
    val y = arg1 / 3
    arg2 + y
  }

  val tuple1: (Int, String) = (404, "Not found page") // TODO: вот так расписывается тапл
  val tuple2: (Int, String) = 404 -> "Not found page" // TODO: это тот же самый тапл, только вместо запятой стоит стрелочка '->'
  Map((404, "NF"), 500 -> "Server error")             // пример на Map

  // пример из Akka роутера...  type Route = RouteContext => Future[RouteResult]
  case class Ip(host: String, port: Int)

  val toAddress: Ip => String = { ip =>
    s"${ip.host}:${ip.port}"
  }
  val ip: Ip = Ip("localhost", 8080)
  println( "Address = " + toAddress(ip) )

  val addressAsTuple: Ip => (String, Int) = { ip =>
    ip.host -> ip.port
  }
  println( "Address as tuple = " + addressAsTuple(ip) )


  val sum1: (Int, Int) => Int = (a, b) => a + b
  /*
   * TODO: ЕСЛИ У НАС ОДИН АРГУМЕНТ ИСПОЛЬЗУЕТСЯ ТОЛЬКО ОДИН РАЗ И ВТОРОЙ АРГУМЕНТ ИСПОЛЬЗУЕТСЯ ТОЛЬКО ОДИН РАЗ И ОНИ ИСПОЛЬЗУЮТСЯ ПО ПОРЯДКУ
   *       тогда все это можно переписать следующим образом (магия):
   */
  val sum2: (Int, Int) => Int = _ + _            // и такая запись выглядит уже не очень приятно
  val prod: (Int, Int) => Int = (_ * _)          // TODO:  ОПЕРАТОР-ЖОПА  ('Alt' + 'Enter' чтобы перевести такую запись в более приятную форму)
  val prod2: (Int, Int, Int) => Int = _ * _ * _

  // Predicate -> X => Boolean
  // Function
  // Supplier -> X => () // void




  val toTuple: LocalDate => (Int, Int, Int) = { date =>
    (date.getYear, date.getMonthValue, date.getDayOfMonth) // (year, month, day)
  }

  println( toTuple(LocalDate.now) )

  val fromTuple: (Int, Int, Int) => LocalDate = { (year, month, date) =>
    LocalDate.of(year, month, date)
  }

  // Partially applied functions (частично примененные функции)
  val fromTupleThisYear1: (Int, Int) => LocalDate = { (month, date) =>
    fromTuple(LocalDate.now.getYear, month, date) // TODO:  пере используем здесь функцию 'fromTuple' ... в итоге функции порождают новые функции
  }
  // это тоже самое что и:
  val fromTupleThisYear2: (Int, Int) => LocalDate = fromTuple(LocalDate.now.getYear, _, _)
  println( "fromTupleThisYear = " + fromTupleThisYear2(2, 20) )
  // а вот такая запись будет выглядеть более экономично
//  val asArrow: Int => Int => LocalDate = // TODO: Haskell Curry  (функция которая вернет другую функцию)
  val asArrow: Int => Int => Int => LocalDate  =  year => month => day => LocalDate.of (year, month, day)
  println( "asArrow = " + asArrow(2021)(10)(17) )   // TODO: а вот так вызывается функция которая возвращает другую функцию  asArrow(2021)(10)(17)
                                                    // asArrow = 2021-10-17
  /**
    TODO: Вот карри во всей красе:
    - если убрать один параметр        asArrow (2021)(10)      тогда мы получим функцию от одного параметра .. это вернет функцию
    - а если убрать еще один параметр  asArrow (2021)          тогда мы получим функцию от двух параметров ... и это вернет функцию
    - но если вызвать функция так      asArrow (2021)(10)(17)  тогда функция вернет LocalDate ................ а это вернет уже готовый продукт

    TODO: А вот так функция-карри выглядит во время ее использования
    asArrow(2021)(10)(17)
    asArrow.apply(2021).apply(10).apply(17)
   */

  val thisYearMonthDay: Int => Int => LocalDate  =  asArrow( LocalDate.now.getYear )
  println( "thisYearMonthDay = " + thisYearMonthDay(10)(17) )  // TODO: а вот так вызывается функция которая возвращает другую функцию  thisYearMonthDay (10)(17)
                                                               // thisYearMonthDay = 2021-10-17

  val thisYearThisMonthDay: Int => LocalDate  =  asArrow(2021)(10)  // а лучше так   asArrow (LocalDate.now.getYear)(LocalDate.now.getMonthValue)
  println( "thisYearThisMonthDay = " + thisYearThisMonthDay(17) )   // TODO: а вот так вызывается функция которая возвращает другую функцию  thisYearThisMonthDay (17)
                                                                    // thisYearThisMonthDay = 2021-10-17

  /**
   * 1. 'thisYearMonth2: Int => Int'  вот так, функция принимает Int и возвращает Int .......... thisYearMonth2 (10)
   * 2. 'Int => LocalDate ='          а вот так, функция принимает Int и возвращает LocalDate .. thisYearMonth2 (10)(17)
   */
  val thisYearMonthDay2: Int => Int => LocalDate  =  thisYearMonthDay // TODO: функция пере использует другую функцию
  println( "thisYearMonthDay2 = " + thisYearMonthDay2(10)(17) )       // thisYearMonthDay2 = 2021-10-17


  val thisYearThisMonthDay2: Int => LocalDate  =  { day =>
    thisYearMonthDay(LocalDate.now.getMonthValue)(day)                // TODO: функция пере использует другую функцию
  }
  println( "thisYearThisMonthDay2 = " + thisYearThisMonthDay2(17) )   // thisYearThisMonthDay2 = 2021-10-17


  val a: A = new A()
//  a.asDate (10)(1)    // можно писать в таких скобках
//  a.asDate (10){ 1 }  // а еще можно писать и в таких скобках
  a.asDate (10) { entity =>   // пример использования в Akka-HTTP...
    entity
  }

  // такой подход очень часто применяется во многих фреймворках для того чтобы работать асинхронно
  val clazz: A => CurriedClass = a => new CurriedClass("str")(a)


  // ///
  println( "min (10, 1) = " + min(10, 1) )


}

class A {

  // Higher order function (функции высшего порядка)
  def getCurrentYear(uncontract: LocalDate => (Int, Int, Int)): Int = {
    uncontract(LocalDate.now)._1
  }

  def functionDate: LocalDate => (Int, Int, Int) = FunctionApp.toTuple

  // Curring
//  def asDate(x: Int)(y: Int): Int = x + y
  def asDate(x: Int)(y: Int => Int): Int = x + y(1)

}

// каррировать можно не только методы, но даже и конструкторы
class CurriedClass(configStr: String)(a: A)

// через каррирование обычно во всяких классах передается ExecutionContext
class CurriedClass2(configStr: String)(implicit ec: ExecutionContext)

// еще может применяться в DI   Repo => Service => Controller ...

