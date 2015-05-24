package bbc.dojonorth

/**
 * Created by raabyj01 on 24/05/2015.
 */

case class Song(trackId: String, mxmId: Int, words: Map[Int,Int])

case class LyricData(words: Stream[String], songs: Stream[Song])
