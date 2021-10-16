//package com.itea.tree.tree1
//
//import scala.{None => SNone}
//import scala.{Option => SOption}
//
//trait NodeX3
//object No extends NodeX3 {
//  override def toString: String = s"No"
//}
//class Tree(var value: Int = 0, var left: NodeX3 = No, var right: NodeX3 = No) extends NodeX3 {
////class Tree(var value: Int = 0) extends NodeX3 {
////  var left: Tree = new Tree()
////  var right: Tree = new Tree()
//  override def toString: String = s"value=$value, left={$left}, right={$right}"
//}
//
////trait Node1
////case class None() extends Node1 // TODO: для case class обязательно указывать дужки '()' потому-что здесь ожидается автоматическая передача параметров...
////case class BinaryTree1(value: Int, left: Node1, right: Node1) extends Node1
//
//
//
//object BinaryTreeApp extends App {
//
//  val root = new Tree(1)
//  //  tree.value = 2
//  root.left = new Tree(2)
//  root.right = new Tree(3)
//
////  val left2 = root.left
////  left2.left = new Tree(4)
////  root.left.right = new Tree(5)
////
////  root.right.left = new Tree(6)
////  root.right.right = new Tree(7)
////
////  root.right.left.left = new Tree(8)
////  root.right.right.right = new Tree(9)
//
//  println(root)
//
//
//}
