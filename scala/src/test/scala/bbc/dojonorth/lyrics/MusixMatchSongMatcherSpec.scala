package bbc.dojonorth.lyrics

import org.scalatest.{Matchers, FlatSpec}

import scala.collection.immutable.Nil

class MusixMatchSongMatcherSpec extends FlatSpec with Matchers {
  val fixturesDir = "./src/test/resources"
  val testFile = s"$fixturesDir/mxm_sample.txt"

  "A song matcher data loader" should "ignore comment lines" in {
    val lines = MusixMatchSongMatcher.loadDataFile(testFile)
    val commentedLines = lines filter { line => line.startsWith("#") } toList

    commentedLines should be (Nil)
  }

  it should "give an iterator of strings containing <SEP>" in {
    val lines = MusixMatchSongMatcher.loadDataFile(testFile)

    lines foreach { line =>
      line should include ("<SEP>")
    }
  }

  val exampleTrack = MSDTrack("TRMMMKD128F425225D",4418550,"Karkkiautomaatti", "Tanssi vaan")

  "A mxm line parser" should "parse lines beginning with TR into song descriptors" in {

    val exampleLine = "TRMMMKD128F425225D<SEP>Karkkiautomaatti<SEP>Tanssi vaan<SEP>4418550<SEP>Karkkiautomaatti<SEP>Tanssi vaan"

    val parsed = MusixMatchSongMatcher.parseMsdTrackLine(exampleLine)

    parsed should be (exampleTrack)
  }

  "A song metadata loader" should "parse all lines into track data" in {

    val metadata = MusixMatchSongMatcher.loadSongMetaData(testFile)

    metadata.length should be (4)
    metadata should contain (exampleTrack)
  }
}
