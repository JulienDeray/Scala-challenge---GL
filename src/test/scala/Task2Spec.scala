
import org.scalatest._

/**
  * @author : julienderay
  * Created on 13/09/2017
  */

class Task2Spec extends FlatSpec with Matchers {
  "additions only" should "return " in {
    Task2.findExpression(Seq(2, 1, 4), 3) shouldBe "2 + 1"
  }

  "example" should "return (2 + 5) * 6" in {
    Task2.findExpression(Seq(2, 3, 5, 6), 42) shouldBe "6 * (2 + 5)"
  }

  "subtraction" should "return (7 - 1) * 2" in {
    Task2.findExpression(Seq(7, 1, 2), 12) shouldBe "2 * (7 - 1)"
  }

  "multiplications only" should "return 4 * 6" in {
    Task2.findExpression(Seq(4, 2, 6, 6), 24) shouldBe "4 * 6"
  }

  "multiplications only (not affine)" should "return " in {
    Task2.findExpression(Seq(3, 2, 4, 10), 80) shouldBe "2 * 4 * 10"
  }

  "additions only (not affine)" should "return " in {
    Task2.findExpression(Seq(3, 2, 4, 10, 24), 17) shouldBe "3 + 4 + 10"
  }

  "empty list" should "give an appropriate message" in {
    Task2.findExpression(Seq.empty[Int], 100) shouldBe "No expression found"
  }

  "impossible result" should "give an appropriate message" in {
    Task2.findExpression(Seq(1, 2, 3), 100) shouldBe "No expression found"
  }
}
