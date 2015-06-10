package bbc.dojonorth.lyrics

import scala.io.Source
import scala.language.postfixOps
/**
 * This object contains code to read in the Million Song data files
 */
class LyricDataLoader {

  def load(filename: String): LyricData = {
    def lineIterator = loadDataFile(filename)

    val wordsLine = lineIterator filter (_.startsWith("%"))
    val songLines = lineIterator filterNot (_.startsWith("%"))

    val words = parseWordsLine(wordsLine.mkString(","))
    val songs = parseLinesToSongs(songLines, words)

    LyricData(words, songs)
  }

  def loadDataFile(filename: String): Iterator[String] = {
    val allLines = Source.fromFile(filename).getLines()
    allLines.withFilter { ln => !ln.startsWith("#") }
  }

  def parseWordsLine(wordsline: String): Seq[String] = {
    // drop the %, then split by comma, then lowercase
    wordsline drop(1) split(",") map(_.toLowerCase) toSeq
  }

  def parseTrackLine(trackLine: String, words: Seq[String]): SongWords = {
    val splitLine = trackLine.split(",")
    val trackId = splitLine.head
    val mxmId = splitLine.drop(1).head.toInt

    val wordIdMap = splitLine.drop(2).map { wc =>
      // word counts are specified as index to count pairs
      // the index is 1-based, so we convert to 0-based here
      val idAndCount = wc.split(":")
      words(idAndCount(0).toInt - 1) -> idAndCount(1).toInt
    } toMap

    SongWords(trackId, mxmId, wordIdMap)
  }

  def parseLinesToSongs(lines: Iterator[String], words: Seq[String]): Iterator[SongWords] =
    lines map { parseTrackLine(_, words) }
}
