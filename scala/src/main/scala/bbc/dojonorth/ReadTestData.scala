package bbc.dojonorth

/**
 * Just to make sure the test data loads properly...
 * This main class takes the path to your mxm_dataset_test or mxm_dataset_train file
 */
object ReadTestData extends App {

  if (args.length != 1) {
    println("Usage: ReadTestData filename")
  }
  else {
    val songsData = LoadLyricData.load(args(0))

    println("\nFirst five musixmatch IDs:")
    songsData.songs.take(5).foreach { song =>
      println(s"${song.mxmId}")
    }

    println("\nFirst 5 words in words list:")
    songsData.words.take(5).foreach(println)
  }
}
