import scala.util.control.Breaks._

object Calculator {
  def main(args: Array[String]){
    val scanner = new java.util.Scanner(System.in)
    val ton: Automaton = new Automaton
    print("Enter expression: ")
    var input = scanner.nextLine()
    while(input != ""){
      if(ton.check_string(input)) print("Accepted")
      else print("Rejected")
      //print(rpn(input))
      print("\nEnter expression: ")
      input = scanner.nextLine()
    }
    
    print("End of program...")
  }
  
  def rpn(str: String):String = {
    ""
  }
}