package bbc.dojonorth.lyrics

import scala.io.Source

/**
 * This object contains code to read in the Million Song data files
 */
object LoadLyricData {

  def load(filename: String): LyricData = {
    def lineIterator = loadDataFile(filename)

    val wordsLine = lineIterator.filter(_.startsWith("%"))
    val songLines = lineIterator.filterNot(_.startsWith("%"))

    val words = parseWordsLine(wordsLine.mkString(","))
    val songs = parseLinesToSongs(songLines)

    LyricData(words, songs)
  }

  def loadDataFile(filename: String): Iterator[String] = {
    val allLines = Source.fromFile(filename).getLines()
    allLines.withFilter((ln: String) => !ln.startsWith("#"))
  }

  def parseWordsLine(wordsline: String): Seq[String] = {
    wordsline.drop(1).split(",").map(_.toLowerCase).toSeq
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

  def parseLinesToSongs(lines: Iterator[String]): Iterator[Song] = {
    lines.map(parseTrackLine)
  }
}
