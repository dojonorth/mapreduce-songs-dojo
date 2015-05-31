package bbc.dojonorth.lyrics

import scala.io.Source

object MusixMatchSongMatcher {

  def loadSongMetaData(filename: String): Seq[MSDTrack] = {
    val trackLines = loadDataFile(filename)

    trackLines map { line: String =>
      parseMsdTrackLine(line)
    } toSeq
  }

  def loadDataFile(filename: String): Iterator[String] = {
    val allLines = Source.fromFile(filename).getLines()
    allLines withFilter { ln => !ln.startsWith("#") } withFilter { ln => ln.startsWith("TR") }
  }

  def parseMsdTrackLine(line: String): MSDTrack = {
    val splitLine = line.split("<SEP>")
    val trackId = splitLine(0)
    val artist = splitLine(1)
    val title = splitLine(2)
    val mxmId = splitLine(3).toInt

    MSDTrack(trackId, mxmId, artist, title)
  }
}
