/**
  * @author : julienderay
  * Created on 13/09/2017
  */
object Task2 {

  def findExpression(ns: Seq[Int], target: Int): String = {
    case class Affine(x: Int, y: Int = 0, z: Int = 1) {
      override def toString: String = {
        val left = y match {
          case 0 => x.toString
          case y if y < 0 => s"$x - ${y.abs}"
          case _          => s"$x + $y"
        }

        z match {
          case 1 => left
          case _ => s"($left) * $z"
        }
      }

      def test(): Option[Affine] = {
        result() match {
          case n if n == target => Some(this)
          case _                =>
            val subtraction = withSubtraction()
            subtraction.result() match {
              case m if m == target => Some(subtraction)
              case _                => None
            }
        }
      }

      def result(): Int = (x + y) * z

      def withSubtraction(): Affine = this.copy(y = -y)
    }

    def testPermutations: List[Int] => Option[Affine] = {
      case Nil                  => None
      case (x :: Nil)           => Affine(x).test()
      case (x :: y :: Nil)      => Affine(x, y).test()
      case (x :: y :: z :: _)   => Affine(x, y, z).test()
    }

    def testCombination(i: Int): Option[Affine] = {
      ns
        .combinations(i)
        .foldLeft[Option[Affine]](None) {
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