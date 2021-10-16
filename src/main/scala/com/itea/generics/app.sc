
val add = (a: Int, b: Int) => a + b


val addOne: (Int, Int) => Int = (a: Int, b: Int) => a + b



class NodeX1(var value: Int) { // class NodeX1
//  class NodeX1(var value: Int, var left: NodeX1 = ???, var right: NodeX1 = ???) { // scala.NotImplementedError: an implementation is missing
  override def toString: String = s"value=$value"
}

val x1 = new NodeX1(1) // val x1: NodeX1 = value=1
x1.value = 2 // mutated x1.value
x1 // val res0: NodeX1 = value=2


//case class NodeX2(var value: Int = 1) // class NodeX2
//val x2 = NodeX2 // val x2: NodeX2.type = NodeX2
//x2.value = 2 //TODO value value is not a member of object NodeX2
//x2 // val res1: NodeX2.type = NodeX2


trait NodeX3
object No extends NodeX3
class Tree(var value: Int = 1, var left: NodeX3 = No, var right: NodeX3 = No) extends NodeX3 {
  override def toString: String = s"value=$value"
}

val tree = new Tree
tree.value = 2
tree

