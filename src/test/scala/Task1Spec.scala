
import org.scalatest._

/**
  * @author : julienderay
  * Created on 13/09/2017
  */

class Task1Spec extends FlatSpec with Matchers {

    "the example" should "return ABCA" in {
      Task1.lcs("AABACDA", "DACBBCAD") shouldBe "ABCA"
    }

    "left empty" should "return empty" in {
      Task1.lcs("", "DACBBCAD") shouldBe ""
    }

    "right empty" should "return empty" in {
      Task1.lcs("AABACDA", "") shouldBe ""
    }

    "no match" should "return empty" in {
      Task1.lcs("ABCD", "EFGH") shouldBe ""
    }

    "random 1" should "work" in {
      Task1.lcs("AAAB", "BAB") shouldBe "AB"
    }

    "full match" should "return left / right" in {
      Task1.lcs("ABABAB", "ABABAB") shouldBe "ABABAB"
    }
}
