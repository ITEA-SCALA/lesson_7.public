package com.itea.tree

import scala.{None => SNone}
import scala.{Option => SOption}

trait BinaryTree
case class Leaf(v: Int) extends BinaryTree {
//  override def toString: String = s"v=$v" // Branch(2,Branch(4,v=0,v=0),Branch(5,v=0,v=0))
  override def toString: String = ""
}
case class Branch(v: Int, var left: BinaryTree, var right: BinaryTree) extends BinaryTree {
//  override def toString: String = s"$left $right"
}

object BinaryTreeApp extends App {

  val branch = Branch(v = 1,
    left = Branch(v=2,
      left = Branch(v=4, left=Leaf(0), right=Leaf(0)),
      right = Branch(v=5, left=Leaf(0), right=Leaf(0))
    ),
    right = Branch(v=3,
      left = Branch(v=6,
        left = Branch(v=8, left=Leaf(0), right=Leaf(0)),
        right = Leaf(0)
      ),
      right = Branch(v=7,
        left = Leaf(0),
        right = Branch(v=9, left=Leaf(0), right=Leaf(0))
      )
    )
  )

  println( branch )       // Branch(1,Branch(2,Branch(4,Leaf(0),Leaf(0)),Branch(5,Leaf(0),Leaf(0))),Branch(3,Branch(6,Branch(8,Leaf(0),Leaf(0)),Leaf(0)),Branch(7,Leaf(0),Branch(9,Leaf(0),Leaf(0)))))
  println( branch.left )  // Branch(2,Branch(4,Leaf(0),Leaf(0)),Branch(5,Leaf(0),Leaf(0)))
  println( branch.right ) // Branch(3,Branch(6,Branch(8,Leaf(0),Leaf(0)),Leaf(0)),Branch(7,Leaf(0),Branch(9,Leaf(0),Leaf(0))))

  def traversalTree(branch: BinaryTree): BinaryTree = branch match {
    case Branch(_, left, _) => left
    case Branch(_, _, right) => right
    case _ => {
      println( branch )
      branch
    }
  }

//  def traversalTree(branch: BinaryTree): BinaryTree = branch match {
//    case b @ Branch(_,left,right) if (b.left) => left
//    case Leaf(v) => {
//      println( v )
//      branch
//    }
//    case _ => {
//      println( branch )
//      branch
//    }
//  }

  println( traversalTree(branch) )

}
