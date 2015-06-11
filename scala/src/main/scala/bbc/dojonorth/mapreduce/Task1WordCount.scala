package bbc.dojonorth.mapreduce

import bbc.dojonorth.lyrics.ReadTestData._
import org.apache.spark.{SparkContext, SparkConf}
import org.apache.spark.SparkContext._
import collection.immutable.HashMap
import bbc.dojonorth.lyrics.{LyricData, LyricDataLoader, SongWords}

/**
 * The HelloWorld of MapReduce
 */
class Task1WordCount {
  // Importing "Helpers._" brings the Map to HashMap converter into scope
  import Helpers._

  def mapFun(songWords: SongWords): HashMap[String, Int] = {
    ???
  }

  // the reduce function should combine two Maps into a single one with the counts combined for matching words
  def reduceFun(firstMap: HashMap[String,Int], secondMap: HashMap[String,Int]): HashMap[String, Int] = {
    ???
  }
}

object WordCount extends App {
  val lyricDataLoader = new LyricDataLoader
  val wc = new Task1WordCount

  if (args.length != 1) {
    println("Usage: WordCount <path to an MXM file>")
  }
  else {
    val songsData = lyricDataLoader.load(args(0))
    val topWords = processData(songsData)

    // print the word
    topWords.take(20).foreach { case (word,count) =>
      println(word + ": " + count)
    }
  }

  def processData(songsData: LyricData) = {
    val bigDictionary = songsData.songs.map(wc.mapFun).reduceLeft(wc.reduceFun)
    // the comparing function below uses the values in any two dictionary entries
    // ._2 means second element in pair
    bigDictionary.toList.sortWith( (thisDictionary, thatDictionary) => thisDictionary._2 > thatDictionary._2 )
  }
}
