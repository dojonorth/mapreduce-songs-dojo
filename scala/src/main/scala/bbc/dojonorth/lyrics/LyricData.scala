package bbc.dojonorth.lyrics

case class Song(trackId: String, mxmId: Int, words: Map[Int,Int])

case class LyricData(words: Stream[String], songs: Stream[Song])
