package bbc.dojonorth.mapreduce

import bbc.dojonorth.lyrics.SongWords
import org.scalatest.{Matchers, FlatSpec, FunSuite}

class Task1WordCountSpec extends FlatSpec with Matchers {

  "Map Function" should "take a song and return a map of words to word counts" in {
    val wc = new Task1WordCount
    val song = SongWords("tra123", 12345, Map("the" -> 4, "i" -> 5))

    val result = wc.mapFun(song)

    result should contain("the" -> 4)
    result.size should be (2)
  }

  "Reduce Function" should "combine two maps into one" in {
    import Helpers.convertMapToHashMap
    val wc = new Task1WordCount
    val map1 = Map("the" -> 4, "i" -> 5, "you" -> 7)
    val map2 = Map("the" -> 2, "i" -> 1)

    val result = wc.reduceFun(map1, map2)

    result should contain("the" -> 6)
    result should contain("you" -> 7)
    result should contain("i" -> 6)
    result.size should be(3)
  }

}
