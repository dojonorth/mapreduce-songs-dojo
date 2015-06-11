package bbc.dojonorth.mapreduce

import bbc.dojonorth.lyrics.{LyricData, SongWords}

class Task3Grep {

  def grep(word: String, lyricData: LyricData): List[String] = {

    def mapFun(songWords: SongWords): List[String] = {
      ???
    }
    
    def reduceFun(tracksSoFar: List[String], newSong: List[String]): List[String] = {
      ???
    }

    val inputData = lyricData.songs
    ???
  }

  // extension 1: create an App which calls the Grep function with a command line argument for the query word
  // extension 2: return a list of MSDTracks instead of TrackIDs
}
