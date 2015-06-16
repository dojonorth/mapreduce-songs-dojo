package bbc.dojonorth.mapreduce

import bbc.dojonorth.lyrics.{LyricData, LyricDataLoader, SongWords}

class Task1AverageDifferentWords {


}

class Task1AverageTotalWords {


}

/**
 * Example app to use above classes for computing word statistics
 *
 * To run from console:
 * sbt "runMain bbc.dojonorth.mapreduce.WordStats path/to/mxm_dataset_train.txt"
 */
object WordStats extends App {
  val lyricDataLoader = new LyricDataLoader
  val differentWords = new Task1AverageDifferentWords
  val totalWords = new Task1AverageTotalWords

  def getAverageDifferentWords(songsData: LyricData) = {
    ???
  }

  def getAverageTotalWords(songsData: LyricData) = {

    ???
  }

  if (args.length != 1) {
    println("Usage: WordStatistics <path to an MXM file>")
  }
  else {
    val songsData1 = lyricDataLoader.load(args(0))
    val diffWords = getAverageDifferentWords(songsData1)
    println("Average number of different words per song: " + diffWords)

    val songsData2 = lyricDataLoader.load(args(0))
    val totalWords = getAverageTotalWords(songsData2)
    println("Average number of words per song: " + totalWords)
  }
}