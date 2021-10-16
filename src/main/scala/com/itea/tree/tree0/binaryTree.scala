package com.itea.tree.tree0

/**
 * Как двоичное дерево может выглядеть в Scala
 */
sealed trait BinaryTree {

//  def print(): Unit = println( "." ) //TODO:  Д.З. дописать...

  def getOrElse() = this match {
    case b: Branch => {
      print("b=")
//      b
    }
    case l: Leaf => {
      print("l=")
      l.v
    }
  }
}

case class Leaf(v: Int) extends BinaryTree { //TODO:  последний элемент (все значения будут в листках)
  override def toString: String = {
    s"'$v'"
//    s"${getOrElse()}"
  }
}
case class Branch(left: BinaryTree, right: BinaryTree) extends BinaryTree {
  override def toString: String = {
    s"$right $left"
//    s"B={$right} L=$left"
  }
}

object BinaryTreeApp extends App {

  /*
          /\
         /| 3
        5 4
   */

////  Branch( Branch(Left(5), Left(4)), Left(3)).print()
  //
  // {'1', L={'2', L={'4'}, B={'5'}}, B={'3', L={'6', L={'8'}}, B={'7', B={'9'}}}}
  //
  // Branch(
  //        Branch(
  //               Branch(
  //                      Leaf(4), Leaf(5)),
  //                      Leaf(2)),
  //               Branch(
  //                      Branch(
  //                             Branch(
  //                                    Leaf(6), Leaf(7)),
  //                             Leaf(8)),
  //                      Leaf(3)))

  val branch: Branch = Branch(Branch(Branch(Leaf(4), Leaf(5)), Leaf(2)), Branch(Branch(Branch(Leaf(6), Leaf(7)), Leaf(8)), Leaf(3)))


  println( branch ) // '3' '8' '7' '6' '2' '5' '4'

//  println( branch.getOrElse() ) // b=()
  println( branch.left ) // '2' '5' '4'
  println( branch.right ) // '3' '8' '7' '6'

}
