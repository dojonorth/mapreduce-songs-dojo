# mapreduce-songs-dojo


## Getting the songs data

The song lyrics data is provided by Columbia University at:
http://labrosa.ee.columbia.edu/millionsong/musixmatch

There are 2 text files containing the data we need.

If you have WGET installed, you can easily download the zip files with the
following commands:

```
wget http://labrosa.ee.columbia.edu/millionsong/sites/default/files/AdditionalFiles/mxm_dataset_train.txt.zip
wget http://labrosa.ee.columbia.edu/millionsong/sites/default/files/AdditionalFiles/mxm_dataset_test.txt.zip
```

Unzip these files somewhere in the project directory structure.

Each file contains a list of (stemmed) words, followed by a list of track
information. This consists of a Track ID, a musiXmatch ID and a list of word
counts (words are indexed starting from 1).

Code is provided to load these files into Scala streams. You can also read them
into a SQLite database, which will fill up to around 2GB with indexes!
