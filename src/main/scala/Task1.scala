/**
  * @author : julienderay
  * Created on 13/09/2017
  */
object Task1 {
  def lcs(left: String, right: String): String = {
    def lcs: (List[Char], List[Char]) => List[Char] = {
      case (Nil, _)                     => Nil
      case (_, Nil)                     => Nil
      case (l :: ls, r :: rs) if l == r => l :: lcs(ls, rs)
      case (l :: ls, r :: rs) =>
        (lcs(l :: ls, rs), lcs(ls, r :: rs)) match {
          case (lChild, rChild) if lChild.size > rChild.size => lChild
          case (_, rChild)                                   => rChild
        }
    }

    lcs(left.toList, right.toList).mkString
  }
}