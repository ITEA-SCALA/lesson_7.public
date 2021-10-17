package com.itea.functions

import java.time.LocalDate

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

}
