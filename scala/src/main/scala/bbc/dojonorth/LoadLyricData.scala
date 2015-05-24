package bbc.dojonorth

import scala.io.Source

/**
 * This object contains code to read in the Million Song data files
 */
object LoadLyricData {

  def load(filename: String): LyricData = {
    val words = parseWordsLine(loadDataFile(filename).head)
    val songs = parseFileToSongs(filename)

    LyricData(words, songs)
  }

  def loadDataFile(filename: String): Stream[String] = {
    val allLines = Source.fromFile(filename).getLines()
    allLines.filter((ln: String) => !ln.startsWith("#")).toStream
  }

  def parseWordsLine(wordsline: String): Stream[String] = {
    wordsline.drop(1).split(",").map(_.toLowerCase).toStream
  }

  def parseTrackLine(trackLine: String): Song = {
    val splitLine = trackLine.split(",")
    val trackId = splitLine.head
    val mxmId = splitLine.tail.head.toInt

    val wordIdMap = splitLine.tail.tail.map { wc =>
      val idAndCount = wc.split(":")
      idAndCount(0).toInt -> idAndCount(1).toInt
    }.toMap

    Song(trackId, mxmId, wordIdMap)
  }

  def parseFileToSongs(filename: String): Stream[Song] = {
    val songLines = loadDataFile(filename).tail
    for (line <- songLines) yield parseTrackLine(line)
  }

}
