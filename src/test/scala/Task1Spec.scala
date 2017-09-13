
import org.scalatest._

/**
  * @author : julienderay
  * Created on 13/09/2017
  */

class Task1Spec extends FlatSpec with Matchers {

    "the example" should "return ABCA" in {
      Task1.lcs("AABACDA", "DACBBCAD") shouldBe "ABCA"
    }
}
