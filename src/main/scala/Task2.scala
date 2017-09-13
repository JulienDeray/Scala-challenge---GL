/**
  * @author : julienderay
  * Created on 13/09/2017
  */
object Task2 {

  def findExpression(ns: Seq[Int], target: Int): String = {
    case class Affine(x: Int, y: Int = 0, z: Int = 1) {
      override def toString: String = super.toString

      def test(): Option[Affine] = result() match {
        case n if n == target => Some(this)
        case _                => None
      }

      def result(): Int = (x + y) * z
    }

    def testPermutations: List[Int] => Option[Affine] = {
      case Nil                  => None
      case (x :: Nil)           => Affine(x).test()
      case (x :: y :: Nil)      => Affine(x, y).test()
      case (x :: y :: z :: _) => Affine(x, y, z).test()
    }

    def testCombination(i: Int): Option[Affine] = {
      ns.combinations(i).foldLeft[Option[Affine]](None) { case (_, next) => testPermutations(next.toList) } match {
        case Some(res) => Some(res)
        case None if i <= 3 => testCombination(i + 1)
        case None => None
      }
    }

    testCombination(0).fold("No expression found")(_.toString)
  }
}