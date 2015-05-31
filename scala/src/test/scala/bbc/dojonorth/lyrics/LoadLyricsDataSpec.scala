package bbc.dojonorth.lyrics

import org.scalatest.{FlatSpec, Matchers}

import scala.collection.immutable.Nil

class LoadLyricsDataSpec extends FlatSpec with Matchers {

  val fixturesDir = "./src/test/resources"
  val testFile = s"$fixturesDir/lyricdata_sample.txt"

  "A lyric data loader" should "ignore comment lines" in {
    val lines = LoadLyricData.loadDataFile(testFile)
    val commentedLines = lines filter { line => line.startsWith("#") } toList

    commentedLines should be (Nil)
  }

  it should "contain the words list in the first element" in {
    val lines = LoadLyricData.loadDataFile(testFile).toList
    val firstElement: String = lines.head

    firstElement should startWith ("%")
    firstElement should include ("the")
    firstElement should include ("love")
  }

  it should "contain a track sample in the second element" in {
    val lines = LoadLyricData.loadDataFile(testFile).toList

    val secondElement = lines.tail.head // wish I had Scheme's cadr here!

    secondElement should startWith ("TRA")
    secondElement should include ("1548880")
  }

  val wordsLine = "%i,the,you,to,and,a,Me,it,not,in,my,is,of,your,"

  "A words line parser" should "produce words from the string" in {
    val parsedLine = LoadLyricData.parseWordsLine(wordsLine)

    parsedLine should not contain ("%i")
    parsedLine should contain ("i")
    parsedLine should contain inOrder ("of","your")
    parsedLine should have size 14
  }
  it should "only produce lowercase words" in {
    val parsedLine = LoadLyricData.parseWordsLine(wordsLine)

    parsedLine foreach {
      w =>
        w.toLowerCase should be (w)
    }
  }

  "A track line parser" should "extract the track ID, the MusixMatch ID and a map of word ID to count" in {
    val lines = LoadLyricData.loadDataFile(testFile).toList
    val words = LoadLyricData.parseWordsLine(lines.head)

    // helper to make sure 1-based indexing is adhered to
    def getWordForIndex(index: Int) = words(index-1)

    val parsedTrack = LoadLyricData.parseTrackLine(lines.tail.head, words)

    parsedTrack.trackId should be ("TRAABRX12903CC4816")
    parsedTrack.mxmId should be (1548880)
    parsedTrack.words should contain (getWordForIndex(2) -> 19)
    parsedTrack.words should contain (getWordForIndex(30) -> 11)

  }

  val dummySong: SongWords = SongWords("TRAAMYTEST", 1000, Map("the" -> 19, "to" -> 7, "and" -> 6))

  "A song parser" should "produce a stream of 2 songs from the test file" in {

    val songs = LoadLyricData.load(testFile).songs.toList

    songs should contain (dummySong)
    songs.length should be (2)
  }

  "A lyric data loader" should "produce a valid lyric data object" in {
    val fullyParsedLyricData = LoadLyricData.load(testFile)

    fullyParsedLyricData.words should contain ("this")
    fullyParsedLyricData.words should not contain ("jens")


    val songs = fullyParsedLyricData.songs.toList
    songs should contain (dummySong)
    songs.length should be (2)
  }

}