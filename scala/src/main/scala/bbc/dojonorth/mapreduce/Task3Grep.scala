package bbc.dojonorth.mapreduce

import bbc.dojonorth.lyrics.{LyricData, SongWords}

class Task3Grep {

  def grep(word: String, lyricData: LyricData): List[String] = {

    def mapFun(songWords: SongWords): List[String] = {
      ???
    }

    // the reduce function should incorporate songs where the boolean argument is true
    def reduceFun(tracksSoFar: List[String], newSong: List[String]): List[String] = {
      ???
    }

    val inputData = lyricData.songs
    ???
  }
}
