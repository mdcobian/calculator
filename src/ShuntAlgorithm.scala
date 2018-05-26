import scala.collection.mutable.Stack
import scala.collection.mutable.Queue
import scala.math.Ordering.UnitOrdering

class ShuntAlgorithm(input: String) extends UnitOrdering{
  var stack = Stack[String]()
  var queue = Queue[String]()
  var tokens: Array[String] = _
  var operation = ""
  tokens = input.split(" ")
  toRPD()

  def toRPD(): Unit = {
    for(i <- tokens) {
      val token:String = i
      if(this.isInt(token)) {
        queue.enqueue(token)
      }
      else if(this.isOp(token)) {
        if(stack.isEmpty) {
          stack.push(token)
        }
        else if(token.equals("(")) {
          stack.push(token)
        }
        else if(token.equals(")")) {
          while(!stack.top.equals("(")) {
            queue.enqueue(stack.pop())
          }
          stack.pop()
        }
        else {
          var top = checkPrecedence(stack.top)
          while(!stack.isEmpty && 1 == gteq(checkPrecedence(stack.top), checkPrecedence(token))) {
            queue.enqueue(stack.pop())
            if(!stack.isEmpty)
              top = checkPrecedence(stack.top)
          }
          stack.push(token)
        }
      }
      else {
        print("Token: " + token + " not identified, disregarding...\n")
      }
    }
    while(!stack.isEmpty) {
      queue.enqueue(stack.pop())
    }
    while(!queue.isEmpty) {
      operation += queue.dequeue()
      if(!queue.isEmpty)
        operation += ","
    }
  }

  def isInt(input: String) : Boolean = {
    try {
      input.toInt
      true
    }
    catch{
      case e: NumberFormatException => false
    }
  }

  def isOp(input: String) : Boolean = {
    if(input.equals("+") || input.equals("-") || input.equals("*") || input.equals("/") || input.equals("^") || input.equals("(") || input.equals(")"))
      true
    else
      false
  }

  def checkPrecedence(input: String) : AnyVal = {
    val hi:Int = 2
    val med:Int = 1
    val low:Int = 0
    if(input.equals("+") || input.equals("-")) low
    else if(input.equals("*") || input.equals("/")) med
    else if(input.equals("^")) hi
  }

  def getOperation() : String = {
    operation
  }
}
