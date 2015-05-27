package bbc.dojonorth.lyrics

case class Song(trackId: String, mxmId: Int, words: Map[Int,Int])

case class LyricData(words: Seq[String], songs: Iterator[Song])
