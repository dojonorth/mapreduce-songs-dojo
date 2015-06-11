package bbc.dojonorth.mapreduce

import collection.immutable.HashMap
import bbc.dojonorth.lyrics.{LyricData, LyricDataLoader, SongWords}

/**
 * The HelloWorld of MapReduce
 */
class Task1WordCount {
  // Importing "Helpers._" brings the automatic Map to HashMap converter into scope
  import Helpers._

  def mapFun(songWords: SongWords): HashMap[String, Int] = {
    ???
  }

  // the reduce function should combine two Maps into a single one with the counts combined for matching words
  def reduceFun(firstMap: HashMap[String,Int], secondMap: HashMap[String,Int]): HashMap[String, Int] = {
     // hint: replace the question marks
    firstMap.merged(secondMap){ case ((k1,v1), (k2,v2)) => ??? }
  }
}

/**
 * Use the following to test your implementation of the Word Counter code.
 *
> sbt "runMain bbc.dojonorth.mapreduce.WordCount path/to/mxm_dataset_train.txt"
 * Expected output for the top 5 words from the training data:
 * i:	1844508
 * the:	1647308
 * you:	1546262
 * to:	945583
 * and:	937448
 */
object WordCount extends App {
  val lyricDataLoader = new LyricDataLoader
  val wc = new Task1WordCount

  def processData(songsData: LyricData) = {
    val bigDictionary = songsData.songs
      .map(wc.mapFun)
      .reduceLeft(wc.reduceFun)

    // the comparing function below uses the values in any two dictionary entries
    // ._2 means second element in pair
    bigDictionary.toList.sortWith{ (thisDictionary, thatDictionary) =>
      thisDictionary._2 > thatDictionary._2
    }
  }

  if (args.length != 1) {
    println("Usage: WordCount <path to an MXM file>")
  }
  else {
    val songsData = lyricDataLoader.load(args(0))
    val topWords = processData(songsData)

    // print the words and their counts
    topWords.take(20).foreach { case (word,count) =>
      println(word + ":\t" + count)
    }
  }
}
