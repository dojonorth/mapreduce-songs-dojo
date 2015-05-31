package bbc.dojonorth.lyrics

case class SongWords(trackId: String, mxmId: Int, words: Map[String,Int])

case class LyricData(words: Seq[String], songs: Iterator[SongWords])

case class MSDTrack(trackId: String, mxmId: Int, artist: String, title: String)