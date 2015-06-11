package bbc.dojonorth.mapreduce

import bbc.dojonorth.lyrics.{LyricData, SongWords}
import org.scalatest.{Matchers, FlatSpec}

class Task3GrepSpec extends FlatSpec with Matchers {

  "Grep Function" should "find songs containing words" in {
    val grepper = new Task3Grep
    val song = SongWords("tra123", 12345, Map("the" -> 4, "i" -> 5))

    val result = grepper.grep("the", LyricData(Seq("the","test"), Iterator(song)))

    result should contain("tra123")
  }

  it should "not find songs containing missing words" in {
    val grepper = new Task3Grep
    val song = SongWords("tra123", 12345, Map("the" -> 4, "i" -> 5))

    val result = grepper.grep("awesome", LyricData(Seq("the","test"), Iterator(song)))

    result should not contain("tra123")
  }

}
