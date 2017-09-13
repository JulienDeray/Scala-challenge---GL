/**
  * @author : julienderay
  * Created on 13/09/2017
  */
object Task1 {
  def lcs(left: String, right: String): String = {
    val cache = collection.mutable.Map.empty[(List[Char], List[Char]), List[Char]]
    def getOrCompute(l: List[Char], r: List[Char]): List[Char] = cache.getOrElseUpdate((l, r), lcs(l, r))

    def lcs: (List[Char], List[Char]) => List[Char] = {
      case (Nil, _)                     => Nil
      case (_, Nil)                     => Nil
      case (l :: ls, r :: rs) if l == r => l :: getOrCompute(ls, rs)
      case (l :: ls, r :: rs) =>
        (getOrCompute(l :: ls, rs), getOrCompute(ls, r :: rs)) match {
          case (lChild, rChild) if lChild.size > rChild.size => lChild
          case (_, rChild)                                   => rChild
        }
    }

    lcs(left.toList, right.toList).mkString
  }
}