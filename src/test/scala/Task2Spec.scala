
import org.scalatest._

/**
  * @author : julienderay
  * Created on 13/09/2017
  */

class Task2Spec extends FlatSpec with Matchers {
  "addition only" should "work" in {
    Task2.findExpression(Seq(2, 1, 4), 3) shouldBe "2 + 1"
  }

  "example" should "return (2 + 5) * 6" in {
    Task2.findExpression(Seq(2, 3, 5, 6), 42) shouldBe "(2 + 5) * 6"
  }

  "subtraction" should "return (7 - 1) * 2" in {
    Task2.findExpression(Seq(7, 1, 2, 6), 12) shouldBe "(7 - 1) * 2"
  }
}
