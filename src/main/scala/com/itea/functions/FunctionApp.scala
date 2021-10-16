package com.itea.functions

import java.time.LocalDate

object FunctionApp extends App {
  val toTuple: LocalDate => (Int, Int, Int) = { date =>
    (date.getYear, date.getMonthValue, date.getDayOfMonth) // (year, month, day)
  }

  println( toTuple(LocalDate.now) )

}
