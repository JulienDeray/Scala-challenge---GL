
/**
  * @author : julienderay
  * Created on 13/09/2017
  */

object Task1 {
  def lcs(left: String, right: String): String = {
    if (left.length == 0 || right.length == 0) return ""

    (left.last, right.last) match {
      case (l, r) if l == r =>
        lcs(allButLast(left), allButLast(right)) :+ l

      case _ =>
        (lcs(allButLast(left), right), lcs(left, allButLast(right))) match {
          case (lChild, rChild) if lChild.length > rChild.length => lChild
          case (_, rChild)                                       => rChild
        }
    }
  }

  private def allButLast(str: String): String = str.substring(0, str.length - 1)
}