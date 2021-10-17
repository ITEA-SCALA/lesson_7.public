package com.itea.utlis

package object math {

  val min: (Int, Int) => Int = (a, b) =>
    if (a > b) b else a

}
