package bbc.dojonorth.mapreduce

import bbc.dojonorth.lyrics.SongWords
import org.scalatest.{Matchers, FlatSpec}


class Task1WordStatsSpec extends FlatSpec with Matchers {

  "Map Function for different words" should "take a song and return the number of different words" in {
    val wc = new Task1AverageDifferentWords
    val song = SongWords("tra123", 12345, Map("the" -> 4, "i" -> 5))

    //TODO: write your own test!
    fail
    // result should be (2)
  }

  "Reduce Function for different words" should "do something with 2 map results" in {
    val wc = new Task1AverageDifferentWords
    val song = SongWords("tra123", 12345, Map("the" -> 4, "i" -> 5))

    //TODO: write your own test!
    fail
  }

  "Map Function for total words" should "take a song and return the total number of words" in {
    val wc = new Task1AverageTotalWords
    val song = SongWords("tra123", 12345, Map("the" -> 4, "i" -> 5))

    //TODO: write your own test!
    fail
    //    result should be (9)
  }

  "Reduce Function for total words" should "do something with 2 map results" in {
    val wc = new Task1AverageTotalWords
    val song = SongWords("tra123", 12345, Map("the" -> 4, "i" -> 5))

    //TODO: write your own test!
    fail
  }
}
