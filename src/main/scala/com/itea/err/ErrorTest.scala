package com.itea.err

object ErrorTest extends App {

  // 1.
  val res = 5 / 0 //TODO:  ArithmeticException: / by zero
  println( "5 / 0 = " + res )

  /*
   * 2.
   * Сложности с применением для подобного типа 'Either[List[String], T]'
   * + варианты использование в выражениях for-comprehension удобны для решения задач работы со списками... в логических блоках
   * - но такой формат не читабельный для получения выходных результатов...
   */

  /*
   * 3.
   * Использование кастомных Exception-классов в проекте
   * - многократное применение оператора ветвления 'if' приводит засорению логических процессов...
   * + разделение условных проверок в проекте на архитектурном уровне (валидация, получение данных из источника, анализ-обработка данных) является хорошим подходом для поддержания единого порядка в программном коде
   * * кастомные Exception-классы позволяют четко определить:
   *   + такие объекты позволяют многократно пере-использовать внутри проекта
   *   + такие объекты понятны для логики бизнес-модели
   */

  /*
   * 4.
   * Форма представления вывода ошибок и результатов данных...
   */
}
