import scala.annotation.tailrec

/**
  * @author : julienderay
  * Created on 13/09/2017
  */
object Task2 {

  def findExpression(ns: Seq[Int], target: Int): String = {
    sealed trait Expression {
      def result: Int
    }
    case class Number(n: Int) extends Expression {
      def result: Int = n
      override def toString: String = n.toString
    }
    case class Operation(x: Expression, y: Expression, op: Operator) extends Expression {
      def result: Int = op.exec(x.result, y.result)
      override def toString: String = op match {
        case Multiplication =>
          val right = y match {
            case Operation(_, _, Multiplication) => " " + y.toString
            case _: Operation => " (" + y.toString + ")"
            case _ => " " + y.toString
          }
          val left = x match {
            case Operation(_, _, Multiplication) => x.toString + " "
            case _: Operation => " (" + x.toString + ")"
            case _ => x.toString + " "
          }
          left + op.toString + right
        case _ => x.toString + " " + op.toString + " " + y.toString
      }
    }

    sealed trait Operator { def exec(x: Int, y: Int): Int }
    case object Addition extends Operator {
      def exec(x: Int, y: Int): Int = x + y
      override def toString: String = "+"
    }
    case object Subtraction extends Operator {
      def exec(x: Int, y: Int): Int = x - y
      override def toString: String = "-"
    }
    case object Multiplication extends Operator {
      def exec(x: Int, y: Int): Int = x * y
      override def toString: String = "*"
    }
    case object NoOp extends Operator {
      def exec(x: Int, y: Int): Int = -1 // to fill the zip, not used in calculations
      override def toString: String = "/!\\"
    }

    def testPermutation(permutation: List[Int]): Option[String] = {
      val operatorsPermutations = (
          Seq.fill(permutation.length - 1)(Addition) ++
          Seq.fill(permutation.length - 1)(Subtraction) ++
          Seq.fill(permutation.length - 1)(Multiplication)
        ).combinations(permutation.length - 1).flatMap(_.permutations).toSeq

      operatorsPermutations.foldLeft[Option[String]](None) {
        case (Some(res), _) => Some(res)
        case (None, ops) =>
          val binaryTree = buildBinaryTree(permutation.zipAll(ops, 0, NoOp))
          if (binaryTree.result == target) Some(binaryTree.toString)
          else None
      }
    }

    def buildBinaryTree: List[(Int, Operator)] => Expression = {
      case Nil => Number(0) // should never happen
      case (n, NoOp) :: Nil => Number(n)
      case (n, op) :: xs => Operation(Number(n), buildBinaryTree(xs), op)
    }

    def testPermutations(combination: List[Int]): Option[String] = {
      combination.permutations.foldLeft[Option[String]](None) {
        case (Some(res), _)           => Some(res)
        case (None     , permutation) => testPermutation(permutation)
      }
    }

    @tailrec
    def testCombination(i: Int): Option[String] = {
      ns
        .combinations(i)
        .foldLeft[Option[String]](None) {
          case (Some(res), _)    => Some(res)
          case (None     , next) => testPermutations(next.toList)
        }
        match {
          case Some(res)      => Some(res)
          case None if i <= 3 => testCombination(i + 1)
          case None           => None
        }
    }

    testCombination(0).fold("No expression found")(_.toString)
  }
}