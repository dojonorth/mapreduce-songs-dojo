package bbc.dojonorth.lyrics

import scala.io.Source
import scala.language.postfixOps

/**
 * This class loads song titles and artists from the MSD matches file
 * Download the source data at: http://labrosa.ee.columbia.edu/millionsong/sites/default/files/AdditionalFiles/mxm_779k_matches.txt.zip
 */
class MusixMatchSongMatcher {

  def loadSongMetaData(filename: String): Map[String,MSDTrack] = {
    val trackLines = loadDataFile(filename)

    trackLines map { line: String =>
      val track = parseMsdTrackLine(line)
      (track.trackId -> track)
    } toMap
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
    val mxmId = splitLine(3) toInt

    MSDTrack(trackId, mxmId, artist, title)
  }
}
