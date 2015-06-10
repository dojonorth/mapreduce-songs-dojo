package bbc.dojonorth.lyrics

/**
 * Just to make sure the test data loads properly...
 * This main class takes the path to your mxm_dataset_test or mxm_dataset_train file
 */
object ReadTestData extends App {

  val loader = new LyricDataLoader

  if (args.length != 1) {
    println("Usage: ReadTestData <path to an MXM file>")
  }
  else {
    val songsData = loader.load(args(0))

    println("\nFirst five track IDs:")
    songsData.songs.take(5).foreach { song =>
      println(s"${song.trackId}")
    }

    println("\nFirst 5 words in words list:")
    songsData.words.take(5).foreach(println)
  }
}
